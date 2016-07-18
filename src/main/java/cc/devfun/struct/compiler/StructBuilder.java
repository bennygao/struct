package cc.devfun.struct.compiler;

import cc.devfun.struct.compiler.model.*;
import cc.devfun.struct.compiler.parser.StructBaseListener;
import cc.devfun.struct.compiler.parser.StructLexer;
import cc.devfun.struct.compiler.parser.StructParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.util.*;

public class StructBuilder extends StructBaseListener {
    private File src;
    private CommonTokenStream tokens;
    private Map<Integer, Token> usedComments;
    private Map<String, Struct> allStructs;
    private Stack<Struct> structStack;
    private DataType currentType;
    private DefaultValue defaultValue;
    private ArrayDecl currentArray;
    private StructCompiler compiler;

    public StructBuilder(File src, CommonTokenStream tokens, StructCompiler compiler) {
        this.src = src;
        this.tokens = tokens;
        this.compiler = compiler;
        this.allStructs = compiler.getAllStructs();

        this.structStack = new Stack<>();
        this.defaultValue = null;
        this.currentArray = null;
        this.usedComments = new HashMap<>();
    }

    public Map<String, Struct> getAllStructs() {
        return allStructs;
    }

    private int getArraySize(String txt) {
        int posi = txt.indexOf('[');
        int len = txt.length();
        return Integer.parseInt(txt.substring(posi + 1, len - 1));
    }

    private String[] trimComments(String txt) {
        String c = txt.trim();
        if (c.startsWith("/**")) {
            c = c.substring(3);
        } else if (c.startsWith("/*") || c.startsWith("//")) {
            c = c.substring(2);
        }

        int len = c.length();
        if (c.endsWith("*/")) {
            c = c.substring(0, len -2);
        }

        return c.trim().split("\\r\\n|\\n");
    }

    private void addComments(Commentable target, Token token) {
        for (String c : trimComments(token.getText())) {
            target.addComment(c.trim());
        }
        usedComments.put(token.getTokenIndex(), token);
    }

    private void setComments(Commentable obj, ParserRuleContext ctx) {
        Token semi = ctx.getStart();
        int start = semi.getTokenIndex(), index;
        List<Token> tokenList = tokens.getTokens();
        Token token;
        for (index = 0; index < start; ++index) {
            token = tokenList.get(index);
            if (token.getChannel() == StructLexer.HIDDEN && !usedComments.containsKey(index)) {
                addComments(obj, token);
            }
        }

        // 向后看一个token,如果是单行注释,并且与obj在同一行,则把注释内容也加上去。
        semi = ctx.getStop();
        int next = semi.getTokenIndex() + 1;
        if (next < tokenList.size()) {
            token = tokenList.get(next);
            if (token.getChannel() == StructLexer.HIDDEN && !usedComments.containsKey(index)) {
                if (semi.getLine() == token.getLine()) {
                    addComments(obj, token);
                }
            }
        }
    }

    @Override
    public void enterProg(StructParser.ProgContext ctx) {
        super.enterProg(ctx);
    }

    @Override
    public void exitProg(StructParser.ProgContext ctx) {
        super.exitProg(ctx);
    }

    @Override
    public void exitInclude(StructParser.IncludeContext ctx) {
        try {
            String str = ctx.getChild(1).getText();
            String fileName = str.substring(1, str.length() - 1);
            compiler.parse(fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enterBitfield(StructParser.BitfieldContext ctx) {
        String typeName = ctx.getChild(1).getText();
        enterStructProcess(typeName, ctx);
        super.enterBitfield(ctx);
    }

    @Override
    public void exitBitfield(StructParser.BitfieldContext ctx) {
        exitStructProcess();
        super.exitBitfield(ctx);
    }

    @Override
    public void enterStruct(StructParser.StructContext ctx) {
        String typeName = ctx.getChild(1).getText();
        enterStructProcess(typeName, ctx);
        super.enterStruct(ctx);
    }

    @Override
    public void exitStruct(StructParser.StructContext ctx) {
        exitStructProcess();
        super.exitStruct(ctx);
    }

    private void enterStructProcess(String typeName, ParserRuleContext ctx) {
        Struct currentStruct = allStructs.get(typeName);
        if (currentStruct == null) {
            if (ctx instanceof StructParser.StructContext) {
                currentStruct = new Struct(typeName);
            } else {
                currentStruct = new BitField(typeName);
            }
            allStructs.put(typeName, currentStruct);
        } else if (currentStruct.isResolved()) {
            String errmsg = String.format("%s:%d duplicated defined struct %s",
                    src.getName(), ctx.getStart().getLine(), typeName);
            throw new IllegalSemanticException(errmsg);
        } else {
            // issue#1 [BUG] 1.0.5 struct定义文件中bitfield先引用后定义时，会把bitfield生成成为普通Struct。
            if (ctx instanceof StructParser.BitfieldContext) {
                currentStruct = new BitField(typeName);
                allStructs.put(typeName, currentStruct);
            }
        }
        structStack.push(currentStruct);
        currentStruct.setDefinedLocation(src, ctx.getStart().getLine());
        setComments(currentStruct, ctx);
    }


    private void exitStructProcess() {
        Struct currentStruct = structStack.pop();
        currentType = new StructType(currentStruct);
    }

    @Override
    public void enterBits(StructParser.BitsContext ctx) {
        defaultValue = null;
        super.enterBits(ctx);
    }

    @Override
    public void exitBits(StructParser.BitsContext ctx) {
        Field field = new Field();
        int bitsNum;
        String errmsg;
        try {
            bitsNum = Integer.parseInt(ctx.getChild(2).getText());
            if (bitsNum <= 0) {
                errmsg = String.format("%s:%d bits number cannot less than or equals to 0.",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            } else if (bitsNum > 32) {
                errmsg = String.format("%s:%d bits number cannot larger than 32.",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            }
        } catch (NumberFormatException nfe) {
            errmsg = String.format("%s:%d bits number format error, must be integer.",
                    src.getName(), ctx.getStart().getLine());
            throw new IllegalSemanticException(errmsg, nfe);
        }
        BasicType type = new BasicType("int", bitsNum);
        field.setType(type);
        field.setName(ctx.getChild(0).getText());
        field.setDefaultValue(defaultValue);
        setComments(field, ctx);

        Struct currentStruct = structStack.peek();
        currentStruct.addField(field);
        super.exitBits(ctx);
    }

    @Override
    public void enterField(StructParser.FieldContext ctx) {
        defaultValue = null;
        super.enterField(ctx);
    }

    @Override
    public void exitField(StructParser.FieldContext ctx) {
        Field field = new Field();
        field.setType(currentType);
        String fieldName = ctx.getChild(1).getText();
        field.setName(fieldName);
        if (defaultValue != null) {
            if (defaultValue instanceof StringDefaultValue && !currentType.isString()) {
                String errmsg = String.format("%s:%d const type error",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            } else if (defaultValue instanceof NumberDefaultValue && !currentType.isBasic()) {
                String errmsg = String.format("%s:%d const type error",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            } else if (currentType.hasArray() && !currentType.isString()) {
                String errmsg = String.format("%s:%d cannot specify default value for array",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            } else if (defaultValue instanceof FloatingDefaultValue && !currentType.isFloating()) {
                String errmsg = String.format("%s:%d const type error",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            }
        }
        field.setDefaultValue(defaultValue);
        setComments(field, ctx);

        Struct currentStruct = structStack.peek();
        currentStruct.addField(field);

//        currentType = null;
        currentArray = null;
    }

    @Override
    public void exitBasicType(StructParser.BasicTypeContext ctx) {
        String typeName = ctx.getChild(0).getText();
        currentType = new BasicType(typeName);
        super.exitBasicType(ctx);
    }

    @Override
    public void exitStringType(StructParser.StringTypeContext ctx) {
        String typeName = ctx.getChild(0).getText();
        currentType = new StringType(typeName);
        super.exitStringType(ctx);
    }

    @Override
    public void exitStructType(StructParser.StructTypeContext ctx) {
        String typeName = ctx.getChild(0).getText();
        Struct struct = allStructs.get(typeName);
        if (struct == null) {
            struct = new Struct(typeName);
            allStructs.put(typeName, struct);
        }

        StructType structType = new StructType(struct);
        currentType = structType;
        super.exitStructType(ctx);
    }

    @Override
    public void enterArray(StructParser.ArrayContext ctx) {
        super.enterArray(ctx);
    }

    @Override
    public void exitArray(StructParser.ArrayContext ctx) {
        if (ctx.getChild(0).getChildCount() == 3) { // 方括号里有参数
            currentArray = new ArrayDecl(ctx.getChild(0).getChild(1).getText());
        } else { // 方括号里没参数, 是变长数组。
            currentArray = new ArrayDecl();
        }
    }

    @Override
    public void exitType(StructParser.TypeContext ctx) {
        // 在语意层对数组的限制为:
        // 1. basicType 支持定长[5]和变长[], 不支持由字段指定长度的数组[num];
        // 2. stringType 仅支持定长数组[5], 而且必须有长度指定。长度指的是字符串的长度,而不是String的数组;
        // 3. structType 支持所有3中类型, [5] [] [num];
        if (currentArray != null) {
            if (currentType.isBasic() && currentArray.isIdentifier()) {
                String errmsg = String.format("%s:%d array index of baisc type cannot be identifer.",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            } else if (currentType.isString() && !currentArray.isFixed()) {
                String errmsg = String.format("%s:%d length of string must be number.",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            }
        } else if (currentType.isString()) {
            String errmsg = String.format("%s:%d length of string must be specified by a number.",
                    src.getName(), ctx.getStart().getLine());
            throw new IllegalSemanticException(errmsg);
        }

        currentType.setArray(currentArray);
    }

    @Override
    public void exitFloatDefaultValue(StructParser.FloatDefaultValueContext ctx) {
        defaultValue = new FloatingDefaultValue(ctx.getChild(1).getText());
        super.exitFloatDefaultValue(ctx);
    }

    @Override
    public void exitNumberDefaultValue(StructParser.NumberDefaultValueContext ctx) {
        defaultValue = new NumberDefaultValue(ctx.getChild(1).getText());
        super.exitNumberDefaultValue(ctx);
    }

    @Override
    public void exitStringDefaultValue(StructParser.StringDefaultValueContext ctx) {
        defaultValue = new StringDefaultValue(ctx.getChild(1).getText());
        super.exitStringDefaultValue(ctx);
    }
}

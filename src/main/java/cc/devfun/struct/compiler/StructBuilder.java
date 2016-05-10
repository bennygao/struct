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
    private Map<String, StructType> allStructs;
    private StructType currentStruct;
    private DataType currentType;
    private DefaultValue defaultValue;
    private StructCompiler compiler;

    public StructBuilder(File src, CommonTokenStream tokens, StructCompiler compiler) {
        this.src = src;
        this.tokens = tokens;
        this.compiler = compiler;
        this.allStructs = compiler.getAllStructs();

        this.currentStruct = null;
        this.defaultValue = null;
        this.usedComments = new HashMap<>();
    }

    public Map<String, StructType> getAllStructs() {
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

    private void setComments(Commentable obj, ParserRuleContext ctx) {
        Token semi = ctx.getStart();
        int start = semi.getTokenIndex(), index;
        List<Token> tokenList = tokens.getTokens();
        for (Token t : tokenList) {
            index = t.getTokenIndex();
            if (start > index && t.getChannel() == StructLexer.HIDDEN && !usedComments.containsKey(index)) {
                for (String c : trimComments(t.getText())) {
                    obj.addComment(c.trim());
                }
                usedComments.put(t.getTokenIndex(), t);
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
    public void enterStruct(StructParser.StructContext ctx) {
        String typeName = ctx.getChild(1).getText();
        currentStruct = allStructs.get(typeName);
        if (currentStruct == null) {
            currentStruct = new StructType(typeName);
            allStructs.put(typeName, currentStruct);
        } else if (currentStruct.isResolved()) {
            String errmsg = String.format("%s:%d duplicated defined struct %s",
                    src.getName(), ctx.getStart().getLine(), typeName);
            throw new IllegalSemanticException(errmsg);
        }
        currentStruct.setDefinedLocation(src, ctx.getStart().getLine());
        setComments(currentStruct, ctx);
        super.enterStruct(ctx);
    }

    @Override
    public void exitStruct(StructParser.StructContext ctx) {
        allStructs.put(currentStruct.getName(), currentStruct);
        super.exitStruct(ctx);
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
        field.setName(ctx.getChild(1).getText());
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
        currentStruct.addField(field);
        super.exitField(ctx);
    }

    @Override
    public void exitType(StructParser.TypeContext ctx) {
        String typeName = ctx.getChild(0).getText();
        String arraySize = "1";
        if (ctx.getChildCount() > 1) {
            String arrayDef = ctx.getChild(1).getText();
            int posi =  arrayDef.indexOf('[');
            arraySize = arrayDef.substring(posi + 1, arrayDef.length() - 1);
        }
        boolean fixedLength = DataType.isFixedLength(arraySize);

        if (DataType.isBasic(typeName)) {
            currentType = new BasicType(typeName, arraySize);
        } else if (DataType.isString(typeName)) {
            if (!fixedLength) {
                String errmsg = String.format("%s:%d length of string cannot be variable",
                        src.getName(), ctx.getStart().getLine());
                throw new IllegalSemanticException(errmsg);
            }
            currentType = new StringType(typeName, arraySize);
        } else {
            if (!fixedLength) {
                Field numField = currentStruct.getField(arraySize);
                if (numField == null) {
                    String errmsg = String.format("%s:%d undefined variable array index %s",
                            src.getName(), ctx.getStart().getLine(), arraySize);
                    throw new IllegalSemanticException(errmsg);
                } else if (!numField.getType().arrayIndex()) {
                    String errmsg = String.format("%s:%d %s cannot be variable array index",
                            src.getName(), ctx.getStart().getLine(), arraySize);
                    throw new IllegalSemanticException(errmsg);
                }
            }
            StructType st = allStructs.get(typeName);
            if (st == null) {
                st = new StructType(typeName, arraySize);
                allStructs.put(typeName, st);
            } else {
                st.setArraySize(arraySize);
            }
            currentType = st;
        }

        super.exitType(ctx);
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

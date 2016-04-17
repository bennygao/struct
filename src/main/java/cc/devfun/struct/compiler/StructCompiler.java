package cc.devfun.struct.compiler;

import cc.devfun.struct.compiler.codegenerator.HtmlGeneratorFactory;
import cc.devfun.struct.compiler.codegenerator.J2seCodeGeneratorFactory;
import cc.devfun.struct.compiler.model.StructType;
import cc.devfun.struct.compiler.parser.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.Map;

public class StructCompiler {
    public static Map<String, StructType> parse(File defineFile) throws Exception {
        InputStream is = new FileInputStream(defineFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        StructLexer lexer = new StructLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StructParser parser = new StructParser(tokens);
        ParseTree tree = parser.prog(); // parse
        if (parser.getNumberOfSyntaxErrors() > 0) {
            return null;
        }

        ParseTreeWalker walker = new ParseTreeWalker();
        StructBuilder builder = new StructBuilder(defineFile, tokens);
        walker.walk(builder, tree);
        Map<String, StructType> allStructs = builder.getAllStructs();
        for (StructType st : allStructs.values()) {
            if (!st.isResolved()) {
                throw new IllegalSemanticException("未定义的struct " + st.getName());
            }
        }
        return allStructs;
    }

    private static void usage(Options options) {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("MessageCompiler <options> <struct-define-file>",
                options);
        System.out.println();

        System.out.println("生成HTML文档");
        System.out
                .println("MessageCompiler -html -encoding <Encoding> -outputencoding <Encoding> -outdir <Dir> <struct-define-file>");
        System.out
                .println(" -encoding <Encoding> 指定定义文件的字符编码，例如UTF8、gb2312。");
        System.out
                .println(" -outputencoding <Encoding> 指定生成HTML的字符编码，例如UTF8、gb2312。");
        System.out.println(" -outdir <Dir> 指定存放生成HTML的路径。");
        System.out.println(" <struct-define-file> 消息定义文件。");
        System.out.println();

        System.out.println("生成Java2 SE代码");
        System.out
                .println("MessageCompiler -j2se -encoding <Encoding> -outputencoding <Encoding> -outdir <Dir> -package <Package> <struct-define-file>");
        System.out
                .println(" -encoding <Encoding> 指定定义文件的字符编码，例如UTF8、gb2312。");
        System.out.println(" -outputencoding <Encoding> 指定生成源代码的字符编码，例如UTF8、gb2312。");
        System.out.println(" -outdir <Dir> 指定存放生成源代码的路径。");
        System.out.println(" -package <Package> 指定生成源代码class的package。");
        System.out.println(" <struct-define-file> 消息定义文件。");
        System.out.println();
    }

    public static void main(String[] args) {
        // create the command line parser
        CommandLineParser parser = new PosixParser();

        // create the Options
        Options options = new Options();
        options.addOption(OptionBuilder.withDescription("生成html格式文档。").create("html"));
        options.addOption(OptionBuilder.withDescription("生成J2SE代码。").create("java"));
        options.addOption(OptionBuilder.withArgName("OutputDirectory").hasArg()
                .withDescription("存放html格式文档的目录。").create("outdir"));
        options.addOption(OptionBuilder.withArgName("Encoding").hasArg()
                .withDescription("生成源文件字符编码。").create("encoding"));
        options.addOption(OptionBuilder.withArgName("Encoding").hasArg()
                .withDescription("MDL文件字符编码。").create("outputencoding"));
        options.addOption(OptionBuilder.withArgName("Dir").hasArg()
                .withDescription("源代码目录。").create("srcdir"));
        options.addOption(OptionBuilder.withArgName("Package").hasArg()
                .withDescription("生成代码的package。").create("package"));
        options.addOption(OptionBuilder.withArgName("Prefix").hasArg()
                .withDescription("生成消息实体类名的前缀。").create("prefix"));
        options.addOption("h", "help", false, "打印帮助信息。");

        boolean isJava = false;
        boolean isHtml = false;
        int actionNum = 0;
        GeneratorContext ctx = new GeneratorContext();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                usage(options);
                System.exit(0);
            }

            if (line.hasOption("java")) {
                isJava = true;
                ++actionNum;
            }

            if (line.hasOption("html")) {
                isHtml = true;
                ++actionNum;
            }

            if (actionNum > 1) {
                System.err.println("错误：-java -html 选项中只能指定一个。");
                usage(options);
                System.exit(1);
            } else if (actionNum == 0) {
                System.err.println("错误：-java -html 选项必须指定一个。");
                usage(options);
                System.exit(1);
            }

            if (line.hasOption("encoding")) {
                ctx.setEncoding(line.getOptionValue("encoding"));
            }

            if (line.hasOption("outputencoding")) {
                ctx.setOutputEncoding(line.getOptionValue("outputencoding"));
            }

            if (line.hasOption("prefix")) {
                ctx.setPrefix(line.getOptionValue("prefix"));
            }

            if (!line.hasOption("outdir")) {
                System.err.println("错误：没有指定生成文件存放的目录。-outdir");
                usage(options);
                System.exit(1);
            } else {
                ctx.setOutputDir(line.getOptionValue("outdir"));
            }

            if (isJava && !line.hasOption("package")) {
                System.err.println("错误：没有指定Java类的package存放的目录。-package");
                usage(options);
                System.exit(1);
            } else {
                ctx.setJavaPackage(line.getOptionValue("package"));
            }

            String[] otherArgs = line.getArgs();
            if (otherArgs.length == 0) {
                System.err.println("错误：没有指定消息定义文件路径名。");
                usage(options);
                System.exit(1);
            } else {
                ctx.setDefineFile(otherArgs[0]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            usage(options);
            System.exit(1);
        }

        try {
            Map<String, StructType> allStructs = parse(ctx.getDefineFile());
            if (allStructs == null) {
                System.err.println("语法错!");
                System.exit(0);
            }

            ctx.setAllStructs(allStructs);
            CodeGeneratorFactory factory;
            CodeGenerator generator;
            if (isJava) {
                factory = new J2seCodeGeneratorFactory();
            } else if (isHtml) {
                factory = new HtmlGeneratorFactory();
            } else {
                throw new UnsupportedOperationException("未知的生成类型");
            }
            generator = factory.createCodeGenerator();
            generator.generate(ctx);
        } catch (IllegalSemanticException ise) {
            System.err.println(ise.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

package cc.devfun.struct.compiler;

import cc.devfun.struct.compiler.codegenerator.CCodeGeneratorFactory;
import cc.devfun.struct.compiler.codegenerator.HtmlGeneratorFactory;
import cc.devfun.struct.compiler.codegenerator.J2seCodeGeneratorFactory;
import cc.devfun.struct.compiler.model.Struct;
import cc.devfun.struct.compiler.parser.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.*;

public class StructCompiler {
    private Map<String, Struct> allStructs;
    private Set<String> parsedFiles;
    private List<String> searchPath;
    private GeneratorContext generatorContext;

    public StructCompiler(GeneratorContext ctx) {
        generatorContext = ctx;
        parsedFiles = new HashSet<>();
        searchPath = new ArrayList<>();
        allStructs = new LinkedHashMap<>();
        Struct struct = new Struct("Struct");
        struct.setResolved();
        allStructs.put(struct.getName(), struct);
    }

    public Map<String, Struct> getAllStructs() {
        return allStructs;
    }

    public Set<String> getParsedFiles() {
        return parsedFiles;
    }

    public Map<String, Struct> parse(String fileName) throws Exception {
        File defineFile;
        if (fileName.startsWith("/")) {
            defineFile = new File(fileName);
            if (!defineFile.exists()) {
                throw new IllegalSemanticException("struct description file not exist: " + fileName);
            } else {
                return parse(defineFile);
            }
        } else {
            for (String path : searchPath) {
                defineFile = new File(path, fileName);
                if (defineFile.exists()) {
                    return parse(defineFile);
                }
            }

            throw new IllegalSemanticException("struct description file not exist: " + fileName);
        }
    }

    public Map<String, Struct> parse(File defineFile) throws Exception {
        if (parsedFiles.contains(defineFile.getAbsolutePath())) {
            return new HashMap<>();
        } else {
            parsedFiles.add(defineFile.getAbsolutePath());
            searchPath.add(defineFile.getParent());
        }

        System.out.println("Compiling " + defineFile.getAbsolutePath());

        ANTLRInputStream input;
        InputStream is = new FileInputStream(defineFile);
        if (generatorContext.getEncoding() == null) {
            input = new ANTLRInputStream(is);
        } else {
            InputStreamReader reader = new InputStreamReader(is, generatorContext.getEncoding());
            input = new ANTLRInputStream(reader);
        }

        StructLexer lexer = new StructLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StructParser parser = new StructParser(tokens);
        ParseTree tree = parser.prog(); // parse
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new IllegalSemanticException("Syntax error.");
        }

        ParseTreeWalker walker = new ParseTreeWalker();
        StructBuilder builder = new StructBuilder(defineFile, tokens, this);
        walker.walk(builder, tree);
        Map<String, Struct> allStructs = builder.getAllStructs();
        for (Struct struct : allStructs.values()) {
            if (!struct.isResolved()) {
                throw new IllegalSemanticException("undefined struct: " + struct.getName());
            }
        }

        return allStructs;
    }

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newArgumentParser("StructCompiler")
                .defaultHelp(true)
                .description("Compile C style struct to generating Java C or HTML.");
        parser.addArgument("-t", "--target")
                .choices("java", "c", "html").required(true)
                .help("Specify target language");
        parser.addArgument("-e", "--encoding").setDefault("utf8")
                .help("Specify charset encoding of struct description file and generated files");
        parser.addArgument("-p", "--package").nargs(1)
                .help("Specify package of generated Java classes");
        parser.addArgument("-b", "--base").nargs(1)
                .help("Specify package of Struct base class");
        parser.addArgument("-d", "--directory").required(true).nargs(1)
                .help("Specify destination directory");
        parser.addArgument("-f", "--file").required(true).nargs(1)
                .help("Specify struct description file");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        GeneratorContext ctx = new GeneratorContext();
        CodeGeneratorFactory factory;

        String target = ns.getString("target");
        if ("java".equalsIgnoreCase(target)) {
            factory = new J2seCodeGeneratorFactory();
        } else if ("c".equalsIgnoreCase(target)) {
            factory = new CCodeGeneratorFactory();
        } else {
            factory = new HtmlGeneratorFactory();
        }

        String encoding = ns.getString("encoding");
        if (encoding != null) {
            ctx.setEncoding(encoding);
            ctx.setOutputEncoding(encoding);
        }

        String dir = ns.getString("directory");
        dir = dir.substring(1, dir.length() - 1);
        ctx.setOutputDir(dir);

        String pkg = ns.getString("package");
        if (pkg != null) {
            pkg = pkg.substring(1, pkg.length() - 1);
            ctx.setJavaPackage(pkg);
        }

        String basePackage = ns.getString("base");
        if (basePackage != null) {
            basePackage = basePackage.substring(1, basePackage.length() - 1);
            ctx.setBasePackage(basePackage);
        }

        String file = ns.getString("file");
        file = file.substring(1, file.length() - 1);
        ctx.setDefineFile(file);

        try {
            StructCompiler compiler = new StructCompiler(ctx);
            Map<String, Struct> allStructs = compiler.parse(ctx.getDefineFile());
            ctx.setAllStructs(allStructs);
            CodeGenerator generator = factory.createCodeGenerator();;
            generator.generate(ctx);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

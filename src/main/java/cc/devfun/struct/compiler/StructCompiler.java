package cc.devfun.struct.compiler;

import cc.devfun.struct.compiler.codegenerator.CCodeGeneratorFactory;
import cc.devfun.struct.compiler.codegenerator.HtmlGeneratorFactory;
import cc.devfun.struct.compiler.codegenerator.J2seCodeGeneratorFactory;
import cc.devfun.struct.compiler.model.StructType;
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
    private Map<String, StructType> allStructs;
    private Set<String> parsedFiles;
    private List<String> searchPath;

    public StructCompiler() {
        parsedFiles = new HashSet<>();
        searchPath = new ArrayList<>();
        allStructs = new LinkedHashMap<>();
        StructType struct = new StructType("Struct", "1");
        struct.setResolved();
        allStructs.put(struct.getName(), struct);
    }

    public Map<String, StructType> getAllStructs() {
        return allStructs;
    }

    public Set<String> getParsedFiles() {
        return parsedFiles;
    }

    public Map<String, StructType> parse(String fileName) throws Exception {
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

    public Map<String, StructType> parse(File defineFile) throws Exception {
        if (parsedFiles.contains(defineFile.getAbsolutePath())) {
            return new HashMap<>();
        } else {
            parsedFiles.add(defineFile.getAbsolutePath());
            searchPath.add(defineFile.getParent());
        }

        System.out.println("Compiling " + defineFile.getAbsolutePath());

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
        StructBuilder builder = new StructBuilder(defineFile, tokens, this);
        walker.walk(builder, tree);
        Map<String, StructType> allStructs = builder.getAllStructs();
        for (StructType st : allStructs.values()) {
            if (!st.isResolved()) {
                throw new IllegalSemanticException("undefined struct: " + st.getName());
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

        String file = ns.getString("file");
        file = file.substring(1, file.length() - 1);
        ctx.setDefineFile(file);

        try {
            StructCompiler compiler = new StructCompiler();
            Map<String, StructType> allStructs = compiler.parse(ctx.getDefineFile());
            if (allStructs == null) {
                System.err.println("Syntax error");
                System.exit(0);
            }

            ctx.setAllStructs(allStructs);
            CodeGenerator generator = factory.createCodeGenerator();;
            generator.generate(ctx);
        } catch (IllegalSemanticException ise) {
            System.err.println(ise.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

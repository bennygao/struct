package cc.devfun.struct.compiler;


import cc.devfun.struct.compiler.model.Struct;
import cc.devfun.struct.compiler.model.StructType;

import java.io.File;
import java.util.Map;

public class GeneratorContext {
    private String encoding;
    private String outputEncoding;
    private String prefix;
    private File outputDir;
    private String javaPackage;
    private String basePackage;
    private File defineFile;
    private Map<String, Struct> allStructs;

    public GeneratorContext() {
        this.encoding = "utf8";
        this.outputEncoding = "utf8";
        this.prefix = "";
        this.outputDir = null;
        this.javaPackage = "";
        this.basePackage = null;
        this.allStructs = null;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String path) {
        setOutputDir(new File(path));
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    public File getDefineFile() {
        return defineFile;
    }

    public void setDefineFile(String path) {
        setDefineFile(new File(path));
    }

    public void setDefineFile(File defineFile) {
        this.defineFile = defineFile;
    }

    public Map<String, Struct> getAllStructs() {
        return allStructs;
    }

    public void setAllStructs(Map<String, Struct> allStructs) {
        this.allStructs = allStructs;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}

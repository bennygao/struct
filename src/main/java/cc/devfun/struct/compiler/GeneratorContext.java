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
    private File defineFile;
    private boolean skipIncludes;
    private Map<String, Struct> allStructs;
    private int includeLevel;

    public GeneratorContext() {
        this.encoding = "utf8";
        this.outputEncoding = "utf8";
        this.prefix = "";
        this.outputDir = null;
        this.javaPackage = "";
        this.allStructs = null;
        this.includeLevel = 0;
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

    public boolean isSkipIncludes() {
        return skipIncludes;
    }

    public void setSkipIncludes(boolean skipIncludes) {
        this.skipIncludes = skipIncludes;
    }

    public void increaseIncludeLevel() {
        ++includeLevel;
    }

    public void decreaseIncludeLevel() {
        --includeLevel;
    }

    public int getIncludeLevel() {
        return includeLevel;
    }
}

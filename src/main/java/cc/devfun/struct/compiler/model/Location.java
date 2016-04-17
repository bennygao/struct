package cc.devfun.struct.compiler.model;

import java.io.File;

public class Location {
    private File file;
    private int line;

    public Location(File file, int line) {
        this.file = file;
        this.line = line;
    }

    public Location() {
        this.file = null;
        this.line = 0;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        if (file == null) {
            return "Unknown:" + line;
        } else {
            return file.getName() + ':' + line;
        }
    }
}

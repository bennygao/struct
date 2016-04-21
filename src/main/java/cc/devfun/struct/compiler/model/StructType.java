package cc.devfun.struct.compiler.model;

import cc.devfun.struct.compiler.Utils;
import com.github.rjeschke.txtmark.Processor;

import java.io.*;
import java.util.*;

public class StructType extends DataType implements Commentable {
    private String name;
    private boolean resolved = false;
    private Map<String, Field> fields = new LinkedHashMap<>();
    private List<String> comments = new ArrayList<String>();
    private Location definedLocation;

    public StructType(String name) {
        this.name = name;
        this.resolved = false;
        this.definedLocation = new Location();
    }

    public StructType(String name, String arraySize) {
        this(name);
        setArraySize(arraySize);
    }

    public void setResolved() {
        this.resolved = true;
    }

    public void setDefinedLocation(File file, int line) {
        definedLocation.setFile(file);
        definedLocation.setLine(line);
        resolved = true;
    }

    public Location getDefinedLocation() {
        return definedLocation;
    }

    public String getName() {
        return name;
    }

    public boolean isBasic() {
        return false;
    }

    public boolean isStruct() {
        return true;
    }

    public boolean isString() {
        return false;
    }

    public boolean isResolved() {
        return resolved;
    }

    public Collection<Field> getFields() {
        return fields.values();
    }

    public void addField(Field f) {
        this.fields.put(f.getName(), f);
    }

    public Field getField(String name) {
        return fields.get(name);
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public boolean haveComments() {
        return comments.size() > 0;
    }

    @Override
    public String getHtmlComments() {
        return Utils.markdown2Html(comments);
    }

    public String getClassName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    @Override
    public String getTypeName() {
        return getClassName();
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        if (!Utils.isEmpty(comments)) {
            pw.println("/**");
            for (String c : comments) {
                pw.println(" * " + c);
            }
            pw.println(" */");
        }
        pw.println("struct " + name + " {");
        StringBuilder sb = new StringBuilder();
        for (Field f : fields.values()) {
            sb.append(f.toString());
        }
        BufferedReader reader = new BufferedReader(new StringReader(sb.toString()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                pw.println("    " + line);
            }
        } catch (Exception ignore) {}
        pw.println("}");
        pw.close();
        return sw.toString();
    }
}

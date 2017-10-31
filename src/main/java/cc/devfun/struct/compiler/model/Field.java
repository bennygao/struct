package cc.devfun.struct.compiler.model;

import cc.devfun.struct.compiler.Utils;
import com.github.rjeschke.txtmark.Processor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Field implements Commentable {
    private String name;
    private DataType type;
    private List<String> comments = new ArrayList<>();
    private DefaultValue defaultValue;
    private Field reference = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public List<String> getComments() {
        return comments;
    }

    @Override
    public String getHtmlComments() {
        return Utils.markdown2Html(comments);
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public boolean haveComments() {
        return comments.size() > 0;
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
        pw.println(String.format("%s %s;", type.toString(), name));
        pw.close();
        return sw.toString();
    }

    public DefaultValue getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(DefaultValue defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Field getReference() {
        return reference;
    }

    public void setReference(Field reference) {
        this.reference = reference;
    }
}

package cc.devfun.struct.compiler.model;

import cc.devfun.struct.compiler.IllegalSemanticException;
import cc.devfun.struct.compiler.Utils;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Struct implements Commentable {
    private final static AtomicInteger UID = new AtomicInteger(0);
    public final static Struct generic = new Struct("Struct", false);

    private int uid;
    private String name;
    private boolean resolved = false;
    private Map<String, Field> fields = new LinkedHashMap<>();
    private List<String> comments = new LinkedList<>();
    private Location definedLocation;
    private Map<String, Struct> dependency;
    private boolean included;

    public static Struct create(String name, boolean include) {
        if (generic.getName().equalsIgnoreCase(name)) {
            return generic;
        } else {
            return new Struct(name, include);
        }
    }

    protected Struct(String name, boolean included) {
        this.uid = UID.getAndIncrement();
        this.name = name;
        this.resolved = false;
        this.included = included;
        this.dependency = new HashMap<>();
        this.definedLocation = new Location();
    }

    public int getUid() {
        return this.uid;
    }

    public boolean isDecodable() {
        for (Field f : fields.values()) {
            if (!f.getType().isDecodable()) {
                return false;
            }
        }

        return true;
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

    public boolean isBitfield() {
        return false;
    }

    public boolean isResolved() {
        return resolved;
    }

    public Collection<Field> getFields() {
        return fields.values();
    }

    public void addField(Field f) {
        if (this.fields.containsKey(f.getName())) {
            throw new IllegalSemanticException(String.format(
                    "Duplicate field name %s in struct %s",
                    f.getName(), name));
        }

        DataType type = f.getType();
        if (type.isStruct()) {
            StructType st = (StructType) type;
            dependency.put(type.getName(), st.getStruct());
        }
        this.fields.put(f.getName(), f);
    }

    public boolean depend(Struct st) {
        if (dependency.containsKey(st.getName())) {
            return true;
        } else {
            for (Struct d : dependency.values()) {
                if (d.depend(st)) {
                    return true;
                }
            }
        }

        return false;
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

    public boolean isIncluded() {
        return included;
    }
}

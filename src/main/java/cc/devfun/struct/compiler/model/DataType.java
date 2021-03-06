package cc.devfun.struct.compiler.model;

import java.util.HashMap;
import java.util.Map;

public abstract class DataType {
    static Map<String, String> basicTypeSet = new HashMap<String, String>() {{
        put("char", "char");
        put("boolean", "boolean");
        put("byte", "byte");
        put("int8", "byte");
        put("uint8", "int");
        put("short", "short");
        put("int16", "short");
        put("uint16", "int");
        put("int", "int");
        put("int32", "int");
        put("uint32", "long");
        put("long", "long");
        put("int64", "long");
        put("uint64", "long");
        put("float", "float");
        put("double", "double");
    }};

    private ArrayDecl array = null;

    public void setArray(ArrayDecl array) {
        this.array = array;
    }

    public ArrayDecl getArray() {
        return array;
    }

    public boolean isFixedLength() {
        return array == null ? true : array.isFixed();
    }

    public boolean canBeArrayLength() {
        String tn = getTypeName();
        return tn.equalsIgnoreCase("byte") || tn.equalsIgnoreCase("short")
                || tn.equalsIgnoreCase("int") || tn.equalsIgnoreCase("long");
    }

    public boolean isFloating() {
        return !canBeArrayLength();
    }

    public String getArraySize() {
        return array == null ? "1" : array.getSize();
    }

    public boolean hasArray() {
        return array != null;
    }

    public String getPrototype() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        if (hasArray()) {
            sb.append('[');
            if (getArray().isFixed() || getArray().isIdentifier()) {
                sb.append(getArraySize());
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static boolean isBasic(String name) {
        return basicTypeSet.containsKey(name);
    }

    public static boolean isString(String name) {
        return name.equalsIgnoreCase("string");
    }

    public abstract String getName();

    public abstract String getTypeName();

    public abstract boolean isBasic();

    public abstract boolean isStruct();

    public abstract boolean isString();

    public abstract boolean isSettable();

    public abstract boolean isDecodable();
}

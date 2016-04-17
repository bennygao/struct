package cc.devfun.struct.compiler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class DataType {
    public static Map<String, String> basicTypeSet = new HashMap<>();

    static {
        basicTypeSet.put("byte", "byte");
        basicTypeSet.put("int8", "byte");
        basicTypeSet.put("uint8", "byte");
        basicTypeSet.put("short", "short");
        basicTypeSet.put("int16", "short");
        basicTypeSet.put("uint16", "short");
        basicTypeSet.put("int", "int");
        basicTypeSet.put("int32", "int");
        basicTypeSet.put("uint32", "int");
        basicTypeSet.put("int64", "long");
        basicTypeSet.put("uint64", "long");
        basicTypeSet.put("float", "float");
        basicTypeSet.put("double", "double");
    }

    public final static Pattern numberPattern = Pattern.compile("^[0-9]+$");
    private String arraySize = "1";

    public static boolean isFixedLength(String num) {
        return numberPattern.matcher(num).find();
    }

    public boolean isFixedLength() {
        return isFixedLength(getArraySize());
    }

    public boolean arrayIndex() {
        String tn = getTypeName();
        return tn.equalsIgnoreCase("byte") || tn.equalsIgnoreCase("short")
                || tn.equalsIgnoreCase("int") || tn.equalsIgnoreCase("long");
    }

    public boolean isFloating() {
        return !arrayIndex();
    }

    public String getArraySize() {
        return arraySize;
    }

    public void setArraySize(String arraySize) {
        this.arraySize = arraySize;
    }

    public boolean hasArray() {
        if (isFixedLength()) {
            return Integer.parseInt(getArraySize()) > 1;
        } else {
            return true;
        }
    }

    public String getPrototype() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        if (hasArray()) {
            sb.append('[').append(getArraySize()).append(']');
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
}

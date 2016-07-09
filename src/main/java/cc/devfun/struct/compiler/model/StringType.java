package cc.devfun.struct.compiler.model;


public class StringType extends DataType {
    private String name;

    public StringType(String name, String arraySize) {
        this.name = name;
        setArraySize(arraySize);
    }

    public StringType(String name, int num) {
        this(name, Integer.toString(num));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTypeName() {
        return "String";
    }

    @Override
    public boolean isBasic() {
        return false;
    }

    @Override
    public boolean isStruct() {
        return false;
    }

    @Override
    public boolean isString() {
        return true;
    }
}

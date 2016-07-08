package cc.devfun.struct.compiler.model;

public class BitsType extends DataType {
    private String name;

    public BitsType(String name, String arraySize) {
        this.name = name;
        setArraySize(arraySize);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTypeName() {
        return "int";
    }

    @Override
    public boolean isBasic() {
        return true;
    }

    @Override
    public boolean isStruct() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }
}

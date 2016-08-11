package cc.devfun.struct.compiler.model;


public class StringType extends DataType {
    private String name;

    public StringType(String name) {
        this.name = name;
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

    @Override
    public boolean isSettable() {
        return true;
    }

    @Override
    public boolean isDecodable() {
        return getArray().isFixed();
    }
}

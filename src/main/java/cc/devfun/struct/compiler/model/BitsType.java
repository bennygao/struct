package cc.devfun.struct.compiler.model;

public class BitsType extends DataType {
    private String name;

    public BitsType(String name) {
        this.name = name;
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

    @Override
    public boolean isSettable() {
        return true;
    }

    @Override
    public boolean isDecodable() {
        return true;
    }
}

package cc.devfun.struct.compiler.model;

public class GenericType extends StructType {
    private int len;

    public GenericType(int len) {
        super(new Struct("Struct", false));
        this.len = len;
    }

    @Override
    public String getPrototype() {
        return String.format("Struct(%d)", len);
    }

    @Override
    public String getConstructArguments() {
        return Integer.toString(len);
    }

    @Override
    public String getName() {
        return "Struct";
    }

    @Override
    public String getTypeName() {
        return getName();
    }

    @Override
    public boolean isBasic() {
        return false;
    }

    @Override
    public boolean isStruct() {
        return true;
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

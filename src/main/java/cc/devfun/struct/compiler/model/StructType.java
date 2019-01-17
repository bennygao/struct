package cc.devfun.struct.compiler.model;

public class StructType extends DataType {
    private Struct struct;

    public StructType(Struct struct) {
        this.struct = struct;
    }

    public Struct getStruct() {
        return this.struct;
    }

    public String getName() {
        return struct.getName();
    }

    @Override
    public String getTypeName() {
        return struct.getTypeName();
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

    @Override
    public boolean isSettable() {
        return !hasArray();
    }

    @Override
    public boolean isDecodable() {
        return !(hasArray() && getArray().isVarlen());
    }

    public String getConstructArguments() {
        return "";
    }
}

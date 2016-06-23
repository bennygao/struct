package cc.devfun.struct.compiler.model;

public class StructType extends DataType {
    private Struct struct;

    public StructType(Struct struct) {
        this.struct = struct;
    }

    public StructType(Struct struct, String arraySize) {
        this(struct);
        setArraySize(arraySize);
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
    public String toString() {
        return struct.getName() + '[' + getArraySize() + ']';
    }
}

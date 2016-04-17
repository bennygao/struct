package cc.devfun.struct.compiler.model;

public class BasicType extends DataType {
    private String name;

    public BasicType(String name) {
        this.name = name;
    }

    public BasicType(String name, String arraySize) {
        this.name = name;
        setArraySize(arraySize);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTypeName() {
        return basicTypeSet.get(name);
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
    public String toString() {
        if (getArraySize().equals("1")) {
            return name;
        } else {
            return String.format("%s[%d]", name, getArraySize());
        }
    }
}

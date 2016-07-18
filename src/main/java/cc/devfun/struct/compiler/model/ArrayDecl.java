package cc.devfun.struct.compiler.model;

public class ArrayDecl {
    enum ArrayType {
        fixed, varlen, identifier
    }

    private String size;
    private ArrayType type;

    public ArrayDecl(String arraySize) {
        try {
            int value = Integer.parseInt(arraySize);
            size = Integer.toString(value);
            type = ArrayType.fixed;
        } catch (Throwable t) {
            size = arraySize;
            type = ArrayType.identifier;
        }
    }

    public ArrayDecl() {
        type = ArrayType.varlen;
        size = "null"; // 编程语言中不能用字符串作设定数组长度, 设为-1容易发现bug。
    }

    public ArrayDecl(int num) {
        type = ArrayType.fixed;
        size = Integer.toString(num);
    }

    public String getSize() {
        return size;
    }

    public boolean isFixed() {
        return type == ArrayType.fixed;
    }

    public boolean isVarlen() {
        return type == ArrayType.varlen;
    }

    public boolean isIdentifier() {
        return type == ArrayType.identifier;
    }
}

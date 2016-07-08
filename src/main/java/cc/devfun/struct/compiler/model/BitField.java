package cc.devfun.struct.compiler.model;

public class BitField extends Struct {
    public BitField(String name) {
        super(name);
    }

    public boolean isBitfield() {
        return true;
    }
}

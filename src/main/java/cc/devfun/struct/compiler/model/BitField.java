package cc.devfun.struct.compiler.model;

public class BitField extends Struct {
    public BitField(String name, boolean included) {
        super(name, included);
    }

    public boolean isBitfield() {
        return true;
    }
}

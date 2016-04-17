/******************************************************************************
 * Demo.java
 * GENERATED BY StructCompiler, DON'T MODIFY MANULLY.
 * Generated Time: Sat Apr 16 13:27:38 CST 2016
 */
package cc.devfun.struct.test.struct;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * struct Demo
 */
public class Demo extends Struct {
    private byte bv;

    private short sv;

    private int iv;

    private long lv;

    private float fv;

    private double dv;

    private String str;

    public Demo() {
        this.bv = (byte) 1;

        this.sv = (short) -13;

        this.iv = (int) 1700;

        this.lv = (long) 8848;

        this.fv = (float) 3.14159;

        this.dv = (double) 3;

        this.str = (String) "hello world";

    }
    public byte getBv() {
        return this.bv;
    }

    public void setBv(byte bv) {
        this.bv = bv;
    }

    public short getSv() {
        return this.sv;
    }

    public void setSv(short sv) {
        this.sv = sv;
    }

    public int getIv() {
        return this.iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public long getLv() {
        return this.lv;
    }

    public void setLv(long lv) {
        this.lv = lv;
    }

    public float getFv() {
        return this.fv;
    }

    public void setFv(float fv) {
        this.fv = fv;
    }

    public double getDv() {
        return this.dv;
    }

    public void setDv(double dv) {
        this.dv = dv;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public int calcSize() {
        int __size = 0;

        __size += 1 * 1;

        __size += 2 * 1;

        __size += 4 * 1;

        __size += 8 * 1;

        __size += 4 * 1;

        __size += 8 * 1;

        __size += 32;

        return __size;
    }

    public void encode(DataOutput dos) throws IOException {
        write(dos, this.bv, byte.class);

        write(dos, this.sv, short.class);

        write(dos, this.iv, int.class);

        write(dos, this.lv, long.class);

        write(dos, this.fv, float.class);

        write(dos, this.dv, double.class);

        writeString(this.str, 32, dos);

    }

    public Struct decode(DataInput dis) throws IOException {
        this.bv = read(dis, byte.class);

        this.sv = read(dis, short.class);

        this.iv = read(dis, int.class);

        this.lv = read(dis, long.class);

        this.fv = read(dis, float.class);

        this.dv = read(dis, double.class);

        this.str = readString(dis, 32);

        return this;
    }
}

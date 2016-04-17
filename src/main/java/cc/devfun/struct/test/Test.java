package cc.devfun.struct.test;

import cc.devfun.struct.test.struct.*;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        Struct.byteOrder = Struct.BIG_ENDIAN;
        Demo demo = new Demo();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        demo.encode(new DataOutputStream(baos));
        byte[] bytes = baos.toByteArray();
        System.out.println(String.format("calcSize=%d encodeSize=%d", demo.calcSize(), bytes.length));
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Demo newone = new Demo();
        newone.decode(new DataInputStream(bais));
        FileOutputStream fos = new FileOutputStream("/tmp/1");
        fos.write(bytes);
        fos.close();
    }
}

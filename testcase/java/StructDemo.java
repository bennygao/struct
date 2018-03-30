package demo;

import demo.test.Color;
import demo.test.ExtendProperty;
import demo.test.Goods;
import demo.test.Shelf;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class StructDemo {
    public static void main(String[] args) throws Exception {
        Shelf shelf = new Shelf();
        List<Goods> list = shelf.getDisplayedGoods();
        Goods goods = new Goods();
        goods.setId(123);
        goods.setName("苹果");
        list.add(goods);
        goods = new Goods();
        goods.setId(456);
        goods.setName("鸭梨");
        list.add(goods);
        Color[] colors = shelf.getColors();
        for (Color c : colors) {
            c.setColor(5);
            c.setTransparency(1);
        }

        byte[] frames = shelf.getFrame();
        for (int i = 0; i < frames.length; ++i) {
            frames[i] = (byte) i;
        }

        System.out.println("size=" + shelf.calcSize());
        OutputStream output = new FileOutputStream("testcase/data.bin");
        shelf.encode(output);
        output.close();
        shelf = null;

        Shelf newone = new Shelf();
        InputStream input = new FileInputStream("testcase/data.bin");
        newone.decode(input);
        input.close();
        System.out.println(newone);
        System.out.println("size=" + newone.calcSize());
    }

    private static void testBitField() throws Exception {
        ExtendProperty ep = new ExtendProperty();
        System.out.println(ep);

        ep.setLayerType(0x01);
        ep.setDecimalCount(0x01);
        ep.setReserved(0x0f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ep.encode(baos);

        byte[] data = baos.toByteArray();
        for (int i = 0; i < data.length; ++i) {
            int mask = 0x80;
            System.out.print(String.format("[%d] %02x  ", i, data[i]));
            for (int j = 0; j < 8; ++j, mask >>= 1) {
                System.out.print((data[i] & mask) != 0 ? 1 : 0);
            }
            System.out.println();
        }
    }
}

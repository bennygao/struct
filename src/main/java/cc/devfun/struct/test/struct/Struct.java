package cc.devfun.struct.test.struct;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class Struct {
    public final static int LITTLE_ENDIAN = 0;
    public final static int BIG_ENDIAN = 1;
    public static int byteOrder = LITTLE_ENDIAN;

    private final static int TRUE = 1;
    private final static int FALSE = 0;

    public void encode(DataOutput dos) throws IOException {
    }

    public Struct decode(DataInput dis) throws IOException {
        return this;
    }

    public <T> T read(DataInput dis, Class<T> clazz) throws IOException {
        if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            return (T) (Character) readChar(dis);
        } else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            return (T) (Byte) readByte(dis);
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return (T) (Boolean) readBoolean(dis);
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            return (T) (Short) readShort(dis);
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return (T) (Integer) readInteger(dis);
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return (T) (Long) readLong(dis);
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return (T) (Float) readFloat(dis);
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return (T) (Double) readDouble(dis);
        } else {
            throw new UnsupportedOperationException("unsupported read operation on class " + clazz.getName());
        }
    }

    public void write(DataOutput dos, Object obj, Class<?> clazz) throws IOException {
        if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            writeChar((Character) obj, dos);
        } else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            writeByte((Byte) obj, dos);
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            writeBoolean((Boolean) obj, dos);
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            writeShort((Short) obj, dos);
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            writeInteger((Integer) obj, dos);
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            writeLong((Long) obj, dos);
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            writeFloat((Float) obj, dos);
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            writeDouble((Double) obj, dos);
        } else {
            throw new UnsupportedOperationException("unsupported write operation on class " + clazz.getName());
        }
    }

    public String readString(DataInput dis, int len) throws IOException {
        byte[] bytes = new byte[len];
        dis.readFully(bytes);
        return getString(bytes);
    }

    public void writeString(String value, int len, DataOutput dos) throws IOException {
        int strlen = isEmpty(value) ? 0 : value.length();
        int wlen = strlen < len ? strlen : len;
        int diff = len - wlen;

        if (wlen > 0) {
            byte[] bytes = value.getBytes();
            dos.write(bytes, 0, wlen);
        }

        if (diff > 0) {
            byte[] buf = new byte[64];
            Arrays.fill(buf, (byte) 0);
            int cnt = diff / buf.length;
            int mod = diff % buf.length;
            for (int i = 0; i < cnt; ++i) {
                dos.write(buf);
            }
            if (mod > 0) {
                dos.write(buf, 0, mod);
            }
        }
    }

    public byte readByte(DataInput dis) throws IOException {
        return dis.readByte();
    }

    public void writeByte(byte value, DataOutput dos) throws IOException {
        dos.writeByte(value);
    }

    public char readChar(DataInput dis) throws IOException {
        return dis.readChar();
    }

    public void writeChar(char value, DataOutput dos) throws IOException {
        dos.writeChar(value);
    }

    public boolean readBoolean(DataInput dis) throws IOException {
        return dis.readByte() == TRUE;
    }

    public void writeBoolean(boolean value, DataOutput dos) throws IOException {
        dos.writeByte(value ? TRUE : FALSE);
    }

    public short readShort(DataInput dis) throws IOException {
        short bits = dis.readShort();
        return byteOrder == LITTLE_ENDIAN ? Short.reverseBytes(bits) : bits;
    }

    public void writeShort(short value, DataOutput dos) throws IOException {
        short bits = byteOrder == LITTLE_ENDIAN ? Short.reverseBytes(value) : value;
        dos.writeShort(bits);
    }

    public int readInteger(DataInput dis) throws IOException {
        int bits = dis.readInt();
        return byteOrder == LITTLE_ENDIAN ? Integer.reverseBytes(bits) : bits;
    }

    public void writeInteger(int value, DataOutput dos) throws IOException {
        int bits = byteOrder == LITTLE_ENDIAN ? Integer.reverseBytes(value) : value;
        dos.writeInt(bits);
    }

    public long readLong(DataInput dis) throws IOException {
        long bits = dis.readLong();
        return byteOrder == LITTLE_ENDIAN ? Long.reverseBytes(bits) : bits;
    }

    public void writeLong(long value, DataOutput dos) throws IOException {
        long bits = byteOrder == LITTLE_ENDIAN ? Long.reverseBytes(value) : value;
        dos.writeLong(bits);
    }

    public float readFloat(DataInput dis) throws IOException {
        float bits = dis.readFloat();
        return byteOrder == LITTLE_ENDIAN ?
                Float.intBitsToFloat(Integer.reverseBytes(Float.floatToIntBits(bits))) :
                bits;
    }

    public void writeFloat(float value, DataOutput dos) throws IOException {
        float bits = byteOrder == LITTLE_ENDIAN ?
                Float.intBitsToFloat(Integer.reverseBytes(Float.floatToIntBits(value))) :
                value;
        dos.writeFloat(bits);

    }

    public double readDouble(DataInput dis) throws IOException {
        double bits = dis.readDouble();
        return byteOrder == LITTLE_ENDIAN ?
                Double.longBitsToDouble(Long.reverseBytes(Double.doubleToLongBits(bits))) :
                bits;
    }

    public void writeDouble(double value, DataOutput dos) throws IOException {
        double bits = byteOrder == LITTLE_ENDIAN ?
                Double.longBitsToDouble(Long.reverseBytes(Double.doubleToLongBits(value))) :
                value;
        dos.writeDouble(bits);
    }

    private boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.length() == 0;
        }
    }

    private String getString(byte[] bytes) {
        int i;
        for (i = 0; i < bytes.length; ++i) {
            if (bytes[i] == 0) {
                break;
            }
        }
        return new String(bytes, 0, i);
    }
}

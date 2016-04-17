/******************************************************************************
 * SetWakeupFrame.java
 * GENERATED BY StructCompiler, DON'T MODIFY MANULLY.
 * Generated Time: Sat Apr 16 13:27:38 CST 2016
 */
package cc.devfun.struct.test.struct;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * struct SetWakeupFrame
 */
public class SetWakeupFrame extends Struct {
    // 唤醒的Set ID
    private byte[] setId;

    // channel
    private byte channel;

    // 唤醒的Set中的group掩码
    private byte groupMask;

    public SetWakeupFrame() {
        this.setId = new byte[4];
        java.util.Arrays.fill(this.setId, (byte) 0);

        this.channel = (byte) 0;

        this.groupMask = (byte) 0;

    }
    public byte[] getSetId() {
        return this.setId;
    }

    public byte getChannel() {
        return this.channel;
    }

    public void setChannel(byte channel) {
        this.channel = channel;
    }

    public byte getGroupMask() {
        return this.groupMask;
    }

    public void setGroupMask(byte groupMask) {
        this.groupMask = groupMask;
    }


    public int calcSize() {
        int __size = 0;

        __size += 1 * 4;

        __size += 1 * 1;

        __size += 1 * 1;

        return __size;
    }

    public void encode(DataOutput dos) throws IOException {
        for (int i = 0; i < 4; ++i) {
            write(dos, this.setId[i], byte.class);
        }

        write(dos, this.channel, byte.class);

        write(dos, this.groupMask, byte.class);

    }

    public Struct decode(DataInput dis) throws IOException {
        for (int i = 0; i < 4; ++i) {
            this.setId[i] = read(dis, byte.class);
        }

        this.channel = read(dis, byte.class);

        this.groupMask = read(dis, byte.class);

        return this;
    }
}

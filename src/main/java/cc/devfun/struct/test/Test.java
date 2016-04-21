package cc.devfun.struct.test;

import cc.devfun.struct.compiler.codegenerator.Utils;
import cc.devfun.struct.test.struct.*;
import com.github.rjeschke.txtmark.Configuration;
import com.github.rjeschke.txtmark.Processor;
import org.apache.velocity.app.Velocity;

import java.io.*;

public class Test {
    private final static String src = "[$PROFILE$]: extended\n[1byte, 8位  \n" +
            "bit[0-3] 4位 WOR时长，用于定义价签进入Group WOR模式后，价签的WOR周期。  \n" +
            "1. 0000: WOR周期1s  \n" +
            "1. 0001: WOR周期2s  \n" +
            "1. 0011: WOR周期4s  \n" +
            "1. 1001: WOR周期6s  \n" +
            "1. 1010: WOR周期8s  \n" +
            "\n" +
            "bit[4-7] 4位 用于定义价签进入Group WOR模式和Group Transfer模式后，价签的RF工作时间。 \n" +
            "1. 0000: work time 1s\n" +
            "1. 0001: work time 2s\n" +
            "1. 0011: work time 4s\n" +
            "1. 1001: work time 6s\n" +
            "1. 1010: work time 8s" +
            "1. 0000: WOR周期1s\n" +
            "1. 0001: WOR周期2s\n" +
            "1. 0011: WOR周期4s\n" +
            "1. 1001: WOR周期6s\n" +
            "1. 1010: WOR周期8s\n";

    private final static String src2 = "[$PROFILE$]: extended\nThis is a paragraph\n" +
            "* and this is not a list";
    public static void main(String[] args) throws Exception {
        Utils utils = Utils.getInstance();
        System.out.println(utils.getCStyleIdentifier("HTPv3Head"));
        System.out.println(utils.getCStyleIdentifier("abcHTPv3Head"));
        System.out.println(utils.getCStyleIdentifier("H"));
        System.out.println(utils.getCStyleIdentifier("SegSeg123456H"));
    }
}

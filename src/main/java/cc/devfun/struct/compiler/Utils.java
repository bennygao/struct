package cc.devfun.struct.compiler;


import java.util.Collection;
import java.util.Map;

public class Utils {
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }

    public static boolean isEmpty(Collection<?> c) {
        if (c == null) {
            return true;
        } else {
            return c.size() == 0;
        }
    }

    public static boolean isEmpty(Map<?, ?> m) {
        if (m == null) {
            return true;
        } else {
            return m.size() == 0;
        }
    }
}

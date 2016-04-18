package cc.devfun.struct.compiler;


import com.github.rjeschke.txtmark.Processor;

import java.io.PrintWriter;
import java.io.StringWriter;
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

    public static String markdown2Html(Collection<String> lines) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("[$PROFILE$]: extended");
        for (String l : lines) {
            pw.println(l);
        }
        return Processor.process(sw.toString());
    }
}

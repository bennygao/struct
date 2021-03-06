package cc.devfun.struct.compiler.codegenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static class SingletonHolder {
        static Utils singleton = new Utils();
    }

    public static Utils getInstance() {
        return SingletonHolder.singleton;
    }

    private Pattern upperPattern;
    private Utils() {
        upperPattern = Pattern.compile("[A-Z]+");
    }


    public String getGetter(String prop) {
        return "get" + upperFirstChar(prop);
    }

    public String getSetter(String prop) {
        return "set" + upperFirstChar(prop);
    }

    public String upperFirstChar(String str) {
        String firstCh = str.substring(0, 1).toUpperCase();
        return firstCh + str.substring(1);
    }

    public String getCStyleIdentifier(String name) {
        if (name.equalsIgnoreCase("struct")) {
            return "Struct";
        }

        StringBuilder sb = new StringBuilder();
        Matcher m = upperPattern.matcher(name);
        int start = 0, b = 0, e = 0;
        List<String> segs = new ArrayList<>();
        while (m.find(start)) {
            b = m.start();
            e = m.end();
            if (b > start) {
                segs.add(name.substring(start, b));
            }
            segs.add(name.substring(b, e));
            start = m.end();
        }

        if (e < name.length()) {
            segs.add(name.substring(e));
        }

        for (int i = 0; i < segs.size(); ++i) {
            if (i > 0) {
                if (segs.get(i - 1).length() == 1 && Character.isUpperCase(segs.get(i - 1).charAt(0))){
                    sb.append(segs.get(i));
                    continue;
                } else {
                    sb.append('_');
                }
            }

            sb.append(segs.get(i).toLowerCase());
        }

        return sb.toString();
    }
}

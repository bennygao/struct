package cc.devfun.struct.compiler.codegenerator;

public class Utils {
    private Utils() {
    }

    static class SingletonHolder {
        static Utils singleton = new Utils();
    }

    public static Utils getInstance() {
        return SingletonHolder.singleton;
    }

    public String getGetter(String prop) {
        String firstCh = prop.substring(0, 1);
        String others = prop.substring(1);
        return "get" + firstCh.toUpperCase() + others;
    }

    public String getSetter(String prop) {
        String firstCh = prop.substring(0, 1);
        String others = prop.substring(1);
        return "set" + firstCh.toUpperCase() + others;
    }
}

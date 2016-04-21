package cc.devfun.struct.compiler.codegenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import cc.devfun.struct.compiler.filter.LineEndFilterWriter;

import org.apache.velocity.app.Velocity;

public class VelocityCodeGenerator {
    private final static String DIGEST_IDENTIFIER = "@DIGEST";

    public VelocityCodeGenerator() {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void init() throws Exception {
        Properties props = new Properties();
        // 模板文件是UTF-8编码
        props.setProperty("input.encoding", "UTF8");

        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.description",
                "Velocity Classpath Resource Loader");
        props.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(props);
    }

    protected String getSourcePath(File path, String pkg) {
        if (pkg == null) {
            return path.getAbsolutePath();
        } else {
            return new StringBuffer(path.getAbsolutePath()).append(File.separatorChar)
                    .append(pkg.replace('.', File.separatorChar)).toString();

        }
    }

    protected Writer getSourceWriter(String pathname, String encoding)
            throws Exception {
        Writer writer;
        if (encoding != null) {
            writer = new OutputStreamWriter(new FileOutputStream(pathname),
                    encoding);
        } else {
            writer = new FileWriter(pathname);
        }

        return new LineEndFilterWriter(writer);
    }
}

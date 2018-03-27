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

    public void init(String encoding) throws Exception {
        Properties props = new Properties();
        // 模板文件是UTF-8编码
        props.setProperty("input.encoding", encoding);

        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.description",
                "Velocity Classpath Resource Loader");
//        props.setProperty("class.resource.loader.class",
//                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        props.setProperty("class.resource.loader.class",
                "cc.devfun.struct.compiler.codegenerator.StructuredGlobbingResourceLoader");
        props.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");
        props.setProperty("runtime.log.invalid.references", "false");
//                "org.apache.velocity.runtime.log.Log4JLogChute");
//        props.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
//        props.setProperty("log4j.logger.org.apache.velocity", "ERROR");
        props.setProperty("runtime.conversion.handler", "none");
        props.setProperty("space.gobbling", "bc");
        Velocity.init(props);
    }

    String getSourcePath(File path, String pkg) {
        if (pkg == null) {
            return path.getAbsolutePath();
        } else {
            return new StringBuilder(path.getAbsolutePath()).append(File.separatorChar)
                    .append(pkg.replace('.', File.separatorChar)).toString();

        }
    }

    Writer getSourceWriter(String pathname, String encoding)
            throws Exception {
        return getSourceWriter(new File(pathname), encoding);
    }

    Writer getSourceWriter(File file, String encoding)
            throws Exception {
        Writer writer;
        if (encoding != null) {
            writer = new OutputStreamWriter(new FileOutputStream(file),
                    encoding);
        } else {
            writer = new FileWriter(file);
        }

        return new LineEndFilterWriter(writer);
    }
}

package cc.devfun.struct.compiler.codegenerator;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.util.ClassUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class StructuredGlobbingResourceLoader extends ClasspathResourceLoader {
    public static class VTLIndentationGlobber extends InputStream {
        private InputStream input;
        private byte[] line;
        private int position;
        private ByteArrayOutputStream lineBuf;

        public VTLIndentationGlobber(InputStream input) {
            this.input = input;
            this.lineBuf = new ByteArrayOutputStream();
            this.line = null;
            this.position = 0;
        }

        private boolean isSpace(byte v) {
            return v == ' ' || v == '\t';
        }

        private byte[] readLine() throws IOException {
            lineBuf.reset();
            int v;
            while ((v = input.read()) >= 0) {
                lineBuf.write(v);
                if (v == '\n') {
                    break;
                }
            }

            if (lineBuf.size() > 0) {
                return lineBuf.toByteArray();
            } else {
                return null;
            }
        }

        @Override
        public int read() throws IOException {
            if (line == null || position >= line.length) {
                if ((line = readLine()) == null) {
                    return -1;
                } else {
                    int start = -1, firstChar = -1, secondChar = -1;
                    for (int i = 0; i < line.length; ++i) {
                        if (!isSpace(line[i])) {
                            firstChar = line[i];
                            if (i < line.length - 1) {
                                secondChar = line[i + 1];
                            }
                            start = i;
                            break;
                        }
                    }

                    if (firstChar == '#' && secondChar != '#' && start > 0) {
                        position = start;
                    } else {
                        position = 0;
                    }
                }
            }

            return Byte.toUnsignedInt(line[position++]);
        }
    }

    @Override
    public Reader getResourceReader(String name, String encoding)
            throws ResourceNotFoundException {
        Reader result = null;

        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("No template name provided");
        }

        /**
         * look for resource in thread classloader first (e.g. WEB-INF\lib in
         * a servlet container) then fall back to the system classloader.
         */

        InputStream rawStream = null;
        try {
            rawStream = ClassUtils.getResourceAsStream(getClass(), name);
            if (rawStream != null) {
                result = buildReader(new VTLIndentationGlobber(rawStream), encoding);
            }
        } catch (Exception fnfe) {
            if (rawStream != null) {
                try {
                    rawStream.close();
                } catch (IOException ioe) {
                }
            }
            throw new ResourceNotFoundException("ClasspathResourceLoader problem with template: " + name, fnfe);
        }

        if (result == null) {
            String msg = "ClasspathResourceLoader Error: cannot find resource " + name;
            throw new ResourceNotFoundException(msg);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        final String txt = "#macro (declareType $field)\n" +
                "    #if ($field.type.isBasic())\n" +
                "        #if ($field.type.hasArray() && !$field.type.isString())\n" +
                "structpp::varray<$typeOfCpp[${field.type.typeName}]>##\n" +
                "        #else\n" +
                "${field.type.typeName}##\n" +
                "        #end\n" +
                "    #elseif ($field.type.isString())\n" +
                "structpp::varray<char>##\n" +
                "    #else\n" +
                "        #if ($field.type.hasArray())\n" +
                "std::vector<${field.type.typeName} *>##\n" +
                "        #else\n" +
                "${field.type.typeName}*##\n" +
                "        #end\n" +
                "    #end\n" +
                "#end";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new VTLIndentationGlobber(new ByteArrayInputStream(txt.getBytes()))));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}

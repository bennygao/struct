package cc.devfun.struct.compiler.codegenerator;

import java.io.File;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.GeneratorContext;
import cc.devfun.struct.compiler.model.StructType;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class J2seCodeGenerator extends VelocityCodeGenerator implements
        CodeGenerator {
    public void generate(GeneratorContext ctx) throws Exception {
        init(ctx.getEncoding());
        VelocityContext vc = new VelocityContext();
        vc.put("package", ctx.getJavaPackage());
        vc.put("createTime", new Date());
        vc.put("utils", Utils.getInstance());

        Map<String, StructType> allStructs = ctx.getAllStructs();
        allStructs.remove("Struct");
        vc.put("allStructs", allStructs.values());

        String path = getSourcePath(ctx.getOutputDir(), ctx.getJavaPackage());
        StringBuilder pathname = new StringBuilder();
        new File(path).mkdirs();

        Template template;
        Writer writer;
        String fileName = "Struct.java";

        template = Velocity.getTemplate("vm/java/Struct.java.vm");
        pathname.append(path).append(File.separatorChar).append(fileName);
        vc.put("fileName", fileName);
        System.out.print("creating " + pathname.toString() + " ... ");
        writer = getSourceWriter(pathname.toString(), ctx.getOutputEncoding());
        template.merge(vc, writer);
        writer.close();
        System.out.println("OK");

        template = Velocity.getTemplate("vm/java/StructInstance.java.vm");
        for (StructType st : ctx.getAllStructs().values()) {
            fileName = st.getClassName() + ".java";
            pathname.setLength(0);
            pathname.append(path).append(File.separatorChar).append(fileName);
            System.out.print("creating " + st.getName() + " to " + pathname.toString() + " ... ");
            vc.put("struct", st);
            vc.put("structName", st.getName());
            writer = getSourceWriter(pathname.toString(), ctx.getOutputEncoding());
            template.merge(vc, writer);
            writer.close();
            System.out.println("OK");
        }
    }
}


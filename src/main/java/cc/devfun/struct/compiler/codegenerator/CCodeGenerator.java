package cc.devfun.struct.compiler.codegenerator;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.GeneratorContext;
import cc.devfun.struct.compiler.model.Struct;
import cc.devfun.struct.compiler.model.StructType;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.Writer;
import java.util.*;

public class CCodeGenerator extends VelocityCodeGenerator implements
        CodeGenerator {
    @Override
    public void generate(GeneratorContext ctx) throws Exception {
        init(ctx.getEncoding());
        File outdir = ctx.getOutputDir();
        outdir.mkdirs();

        VelocityContext vc = new VelocityContext();
        vc.put("createTime", new Date());
        vc.put("utils", Utils.getInstance());
        vc.put("charset", ctx.getOutputEncoding());

        File f = ctx.getDefineFile();
        String fn = f.getName();
        int posi = fn.lastIndexOf('.');
        if (posi > 0) {
            fn = fn.substring(0, posi);
        }
        vc.put("fileName", fn.toUpperCase());

        Map<String, Struct> allStructs = ctx.getAllStructs();
        allStructs.remove("Struct");
        List<Struct> list = new ArrayList<>();
        list.addAll(allStructs.values());
        Collections.sort(list, new Comparator<Struct>() {
            @Override
            public int compare(Struct o1, Struct o2) {
                if (o1.depend(o2)) {
                    return 1;
                } else if (o2.depend(o1)) {
                    return -1;
                } else {
                    return o1.getTypeName().compareTo(o2.getTypeName());
                }
            }
        });
        vc.put("allStructs", list);

        Template template;
        Writer writer;
        StringBuilder pathname = new StringBuilder();
        File html;

        template = Velocity.getTemplate("vm/c/Header.h.vm");
        html = new File(outdir, fn.toLowerCase() + ".h");
        System.out.print("creating " + html.getAbsolutePath() + " ... ");
        writer = getSourceWriter(html.getAbsolutePath(), ctx.getOutputEncoding());
        template.merge(vc, writer);
        writer.close();
        System.out.println("OK");
    }
}

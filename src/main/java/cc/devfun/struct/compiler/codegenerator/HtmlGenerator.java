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

public class HtmlGenerator extends VelocityCodeGenerator implements
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
        List<Struct> list = new ArrayList<>();
        Map<String, Struct> allStructs = ctx.getAllStructs();
        allStructs.remove("Struct");
        list.addAll(allStructs.values());
        Collections.sort(list, Comparator.comparing(Struct::getTypeName));
        vc.put("allStructs", list);

        Template template;
        Writer writer;
        StringBuilder pathname = new StringBuilder();
        File html;

        template = Velocity.getTemplate("vm/html/index.html.vm");
        html = new File(outdir, "index.html");
        System.out.print("creating " + html.getAbsolutePath() + " ... ");
        writer = getSourceWriter(html.getAbsolutePath(), ctx.getOutputEncoding());
        template.merge(vc, writer);
        writer.close();
        System.out.println("OK");

        template = Velocity.getTemplate("vm/html/Navigation.html.vm");
        html = new File(outdir, "Navigation.html");
        System.out.print("creating " + html.getAbsolutePath() + " ... ");
        writer = getSourceWriter(html.getAbsolutePath(), ctx.getOutputEncoding());
        template.merge(vc, writer);
        writer.close();
        System.out.println("OK");

        template = Velocity.getTemplate("vm/html/Struct.html.vm");
        html = new File(outdir, "Struct.html");
        System.out.print("creating " + html.getAbsolutePath() + " ... ");
        writer = getSourceWriter(html.getAbsolutePath(), ctx.getOutputEncoding());
        template.merge(vc, writer);
        writer.close();
        System.out.println("OK");
    }
}

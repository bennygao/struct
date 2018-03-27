package cc.devfun.struct.compiler.codegenerator;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.GeneratorContext;
import cc.devfun.struct.compiler.model.Struct;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CppCodeGenerator extends VelocityCodeGenerator implements CodeGenerator {

    private String getHppName(GeneratorContext ctx) {
        String defineFileName = ctx.getDefineFile().getName();
        int position = defineFileName.lastIndexOf('.');
        String name = position > 0 ? defineFileName.substring(0, position) : defineFileName;
        return name + ".hpp";
    }

    @Override
    public void generate(GeneratorContext ctx) throws Exception {
        init(ctx.getEncoding());

        if (!ctx.getOutputDir().exists()) {
            if (!ctx.getOutputDir().mkdirs()) {
                throw new IllegalAccessException("cannot create output directory: " + ctx.getOutputDir().getPath());
            }
        } else if (!ctx.getOutputDir().isDirectory()) {
            throw new IllegalArgumentException("specified output directory is not a regular directory");
        }

        List<Struct> structList = ctx.getAllStructs().values().stream()
                .filter(s -> !"Struct".equalsIgnoreCase(s.getName()))
                .filter(s -> !s.isIncluded())
                .collect(Collectors.toList());

        VelocityContext vcSummary = new VelocityContext();
        vcSummary.put("package", ctx.getJavaPackage());
        vcSummary.put("createTime", new Date());
        vcSummary.put("utils", Utils.getInstance());
        vcSummary.put("allStructs", structList);

        String hppName = getHppName(ctx);
        File hppFile = new File(ctx.getOutputDir(), hppName);
        Writer writer = getSourceWriter(hppFile, ctx.getOutputEncoding());
        Template headerTemplate = Velocity.getTemplate("vm/cpp/HppHeader.vm");
        headerTemplate.merge(vcSummary, writer);

        Template template;
        Template structTemplate = Velocity.getTemplate("vm/cpp/StructInstance.hpp.vm");
//        Template bitfieldTemplate = Velocity.getTemplate("vm/java/BitFieldInstance.java.vm");
        for (Struct struct : structList) {
            if (struct.isIncluded() && ctx.isSkipIncludes()) {
                continue;
            }

            VelocityContext vc = new VelocityContext();
            vc.put("package", ctx.getJavaPackage());
            vc.put("createTime", new Date());
            vc.put("utils", Utils.getInstance());
            vc.put("struct", struct);
            vc.put("structName", struct.getName());

            if (struct.isBitfield()) {
//                template = bitfieldTemplate;
                continue;
            } else {
                template = structTemplate;
            }

            template.merge(vc, writer);
        }

        Template footerTemplate = Velocity.getTemplate("vm/cpp/HppFooter.vm");
        footerTemplate.merge(vcSummary, writer);
        writer.close();
    }
}

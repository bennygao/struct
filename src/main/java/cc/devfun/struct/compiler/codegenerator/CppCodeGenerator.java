package cc.devfun.struct.compiler.codegenerator;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.GeneratorContext;
import cc.devfun.struct.compiler.model.Struct;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CppCodeGenerator extends VelocityCodeGenerator implements CodeGenerator {

    private String getBaseName(GeneratorContext ctx) {
        String defineFileName = ctx.getDefineFile().getName();
        int position = defineFileName.lastIndexOf('.');
        return position > 0 ? defineFileName.substring(0, position) : defineFileName;
    }

    @Override
    public void generate(GeneratorContext ctx) throws Exception {
        init(ctx.getEncoding(), "cc.devfun.struct.compiler.codegenerator.StructuredGlobbingResourceLoader");

        if (!ctx.getOutputDir().exists()) {
            if (!ctx.getOutputDir().mkdirs()) {
                throw new IllegalAccessException("cannot create output directory: " + ctx.getOutputDir().getPath());
            }
        } else if (!ctx.getOutputDir().isDirectory()) {
            throw new IllegalArgumentException("specified output directory is not a regular directory");
        }

        List<Struct> structList = ctx.getAllStructs().values().stream()
                .filter(s -> !"Struct".equalsIgnoreCase(s.getName()))
                .collect(Collectors.toList());

        String baseName = getBaseName(ctx);
        String cppName = baseName + ".cpp";
        File cppFile = new File(ctx.getOutputDir(), cppName);
        Template headerTemplate = Velocity.getTemplate("vm/cpp/CppHeader.vm");
        Template structTemplate = Velocity.getTemplate("vm/cpp/StructInstance.cpp.vm");
        Template bitfieldTemplate = Velocity.getTemplate("vm/cpp/BitFieldInstance.cpp.vm");
        Template footerTemplate = Velocity.getTemplate("vm/cpp/CppFooter.vm");
        generateCode(ctx, structList, baseName, cppFile, headerTemplate, structTemplate, bitfieldTemplate, footerTemplate);

        String hppName = baseName + ".hpp";
        File hppFile = new File(ctx.getOutputDir(), hppName);
        headerTemplate = Velocity.getTemplate("vm/cpp/HppHeader.vm");
        structTemplate = Velocity.getTemplate("vm/cpp/StructInstance.hpp.vm");
        bitfieldTemplate = Velocity.getTemplate("vm/cpp/BitFieldInstance.hpp.vm");
        footerTemplate = Velocity.getTemplate("vm/cpp/HppFooter.vm");
        generateCode(ctx, structList, baseName, hppFile, headerTemplate, structTemplate, bitfieldTemplate, footerTemplate);

    }

    private void generateCode(GeneratorContext ctx, Collection<Struct> allStructs, String baseName,
                              File file, Template header, Template struct,
                              Template bitfield, Template footer) throws Exception {

        System.out.print("creating " + file.getCanonicalPath() + " ... ");
        Writer writer = getSourceWriter(file, ctx.getOutputEncoding());

        VelocityContext vcSummary = new VelocityContext();
        vcSummary.put("package", ctx.getJavaPackage());
        vcSummary.put("createTime", new Date());
        vcSummary.put("utils", Utils.getInstance());
        vcSummary.put("allStructs", allStructs);
        vcSummary.put("baseName", baseName);
        header.merge(vcSummary, writer);

        Template template;
        VelocityContext vc = new VelocityContext();
        vc.put("package", ctx.getJavaPackage());
        vc.put("createTime", new Date());
        vc.put("utils", Utils.getInstance());
        for (Struct ss : allStructs) {
            if (ss.isIncluded() && ctx.isSkipIncludes()) {
                continue;
            }

            vc.put("struct", ss);
            vc.put("structName", ss.getName());

            template = ss.isBitfield() ? bitfield : struct;
            template.merge(vc, writer);
        }

        footer.merge(vcSummary, writer);
        writer.close();
        System.out.println("OK");
    }
}

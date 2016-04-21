package cc.devfun.struct.compiler.codegenerator;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.CodeGeneratorFactory;

public class CCodeGeneratorFactory implements CodeGeneratorFactory {
    @Override
    public CodeGenerator createCodeGenerator() throws Exception {
        return new CCodeGenerator();
    }
}

package cc.devfun.struct.compiler.codegenerator;

import cc.devfun.struct.compiler.CodeGenerator;
import cc.devfun.struct.compiler.CodeGeneratorFactory;

public class J2seCodeGeneratorFactory implements CodeGeneratorFactory {
    public CodeGenerator createCodeGenerator() throws Exception {
        return new J2seCodeGenerator();
    }

}

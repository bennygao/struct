package cc.devfun.struct.compiler;

public class IllegalSemanticException extends RuntimeException {
    public IllegalSemanticException(String msg) {
        super(msg);
    }

    public IllegalSemanticException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

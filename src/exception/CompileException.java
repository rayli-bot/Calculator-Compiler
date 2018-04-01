package exception;

public class CompileException extends CoreException {
    public String input;

    public CompileException(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "\u001B[31mCompile Error: " + this.input + "\u001B[0m";
    }
}

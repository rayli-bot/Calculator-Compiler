package exception;

public class OperateException extends CoreException {
    public final float a;
    public final float b;
    public final String operator;

    public OperateException(float a, float b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return  "\u001B[31mOperate Error: " + this.a + " " + operator + " " + this.b + "\u001B[0m";
    }
}

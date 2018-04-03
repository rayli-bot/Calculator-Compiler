package visualization;
import core.expression.Expression;

public class Node implements PrintableNode {
    public Node left, right;
    Expression data;

    public Node(Expression data){
        this.data = data;
    }

    public String getText() {
        return data.getName();
    }

    public PrintableNode getLeft() {
        return left;
    }

    public PrintableNode getRight() {
        return right;
    }

    public Expression getExpr() { return data; }
}
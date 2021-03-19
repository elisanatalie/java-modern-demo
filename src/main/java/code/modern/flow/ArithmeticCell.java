package code.modern.flow;

public class ArithmeticCell extends SimpleCell {
    private int left;
    private int right;

    public ArithmeticCell(final String name) {
        super(name);
    }

    public void setLeft(final int left) {
        this.left = left;
        onNext(left + right);
    }

    public void setRight(final int right) {
        this.right = right;
        onNext(right + left);
    }
}


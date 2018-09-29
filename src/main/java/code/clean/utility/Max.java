package code.clean.utility;

public class Max {
    private final int a;
    private final int b;

    public Max(final int x, final int y) {
        a = x;
        b = y;
    }

    public int intValue() {
        return (a > b) ? a : b;
    }
}
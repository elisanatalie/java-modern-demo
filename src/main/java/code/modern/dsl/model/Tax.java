package code.modern.dsl.model;

public class Tax {
    public static double regional(final double value) {
        return value * 1.1;
    }

    public static double general(final double value) {
        return value * 1.3;
    }

    public static double surcharge(final double value) {
        return value * 1.05;
    }
}

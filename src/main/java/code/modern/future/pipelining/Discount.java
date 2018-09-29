package code.modern.future.pipelining;

import static code.modern.future.Util.delay;
import static code.modern.future.Util.format;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(final int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(final Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(final double price, final Code code) {
        delay();
        return format((price * (100 - code.percentage)) / 100);
    }
}


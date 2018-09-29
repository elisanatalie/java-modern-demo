package code.modern.future.pipelining;

import static code.modern.future.Util.format;
import static code.modern.future.Util.randomDelay;

import java.util.Random;

public class ShopWithDiscount {
    private final String name;
    private final Random random = new Random();

    public ShopWithDiscount(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrice(final String product) {
        final double price = calculatePrice(product);
        final Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    private double calculatePrice(final String product) {
        randomDelay();
        return format((random.nextDouble() * product.charAt(0)) + product.charAt(1));
    }
}

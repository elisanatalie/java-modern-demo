package code.modern.future.pipelining;

public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    private Quote(final String shopName, final double price, final Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(final String s) {
        final String[] split = s.split(":");
        final String shopName = split[0];
        final double price = Double.parseDouble(split[1]);
        final Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}

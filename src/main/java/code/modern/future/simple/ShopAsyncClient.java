package code.modern.future.simple;

import java.util.concurrent.Future;

public class ShopAsyncClient {
    public static void main(final String[] args) {
        final Shop shop = new Shop("BestShop");

        final long start = System.nanoTime();

        final Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        final long invocationTime = ((System.nanoTime() - start) / 1_000_000);

        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // Do some more tasks, like querying other shops
        // doSomethingElse();
        // while the price of the product is being calculated
        try {
            final double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        final long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}

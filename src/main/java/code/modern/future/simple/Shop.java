package code.modern.future.simple;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private final String name;
    private final Random random = new Random();

    public Shop(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(final String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(final String product) {
//        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(() -> {
//            try {
//                double price = calculatePrice(product);
//                futurePrice.complete(price);
//            } catch (final Exception ex) {
//                futurePrice.completeExceptionally(ex);
//            }
//        }).start();
//
//        return futurePrice;
// This code below and above are equivalent
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(final String product) {
        delay();
        return (random.nextDouble() * product.charAt(0)) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
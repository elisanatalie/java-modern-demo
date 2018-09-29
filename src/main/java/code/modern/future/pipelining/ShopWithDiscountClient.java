package code.modern.future.pipelining;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ShopWithDiscountClient {
    private static final List<ShopWithDiscount> shops = List.of(new ShopWithDiscount("BestPrice"),
                                                                new ShopWithDiscount("LetsSaveBig"),
                                                                new ShopWithDiscount("MyFavoriteShop"),
                                                                new ShopWithDiscount("BuyItAll"),
                                                                new ShopWithDiscount("Amaton"),
                                                                new ShopWithDiscount("Okko"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(final String[] args) {
        final long start1 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        final long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");

        final long start2 = System.nanoTime();
        System.out.println(findPricesWithCompletableFuture("myPhone27S"));
        final long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");

        long start3 = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27S").map(
                f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start3) / 1_000_000) + " msecs)")))
                                                                    .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start3) / 1_000_000) + " msecs");
    }

    public static List<String> findPrices(final String product) {
        return shops.stream().map(shop -> shop.getPrice(product))
                    .map(Quote::parse)
                    .map(Discount::applyDiscount)
                    .collect(toList());
    }

    public static List<String> findPricesWithCompletableFuture(final String product) {
        final List<CompletableFuture<String>> priceFutures
                = shops.stream()
                       .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                       .map(future -> future.thenApply(Quote::parse))
                       .map(future -> future.thenCompose(
                               quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                       .collect(toList());
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    public static Stream<CompletableFuture<String>> findPricesStream(final String product) {
        return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(
                            quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}

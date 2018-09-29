package code.modern.future.simple;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiShopSyncClient {
    private static final List<Shop> shops = List.of(new Shop("BestPrice"),
                                                    new Shop("LetsSaveBig"),
                                                    new Shop("MyFavoriteShop"),
                                                    new Shop("BuyItAll"),
                                                    new Shop("Amaton"),
                                                    new Shop("Okko"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(final String[] args) {
        final long start1 = System.nanoTime();
        System.out.println(findPricesParallelStream("myPhone27S"));
        final long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");

        final long start2 = System.nanoTime();
        System.out.println(findPricesFuture("myPhone27S"));
        final long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");

        final long start3 = System.nanoTime();
        System.out.println(findPricesFutureWithExecutor("myPhone27S"));
        final long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");

        final long start4 = System.nanoTime();
        System.out.println(findPricesInUSD("myPhone27S"));
        final long duration4 = (System.nanoTime() - start4) / 1_000_000;
        System.out.println("Done in " + duration4 + " msecs");
    }

    public static List<String> findPricesParallelStream(final String product) {
        return shops.parallelStream()
                    .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                    .collect(toList());
    }

    public static List<String> findPricesFuture(final String product) {
        final List<CompletableFuture<String>> priceFutures = shops.stream()
                                                                  .map(shop -> CompletableFuture.supplyAsync(
                                                                          () -> shop.getName() + " price is " + shop.getPrice(product)))
                                                                  .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    public static List<String> findPricesFutureWithExecutor(final String product) {
        final List<CompletableFuture<String>> priceFutures = shops.stream()
                                                                  .map(shop -> CompletableFuture.supplyAsync(
                                                                          () -> shop.getName() + " price is " + shop.getPrice(product),
                                                                          executor))
                                                                  .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    public static List<String> findPricesInUSD(final String product) {
        final List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (final Shop shop : shops) {
            final CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                                     .thenCombine(
                                             CompletableFuture.supplyAsync(
                                                     () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD))
                                                              // Timeout management added in Java 9
                                                              .completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS),
                                             (price, rate) -> price * rate
                                     )
                                     // Timeout management added in Java 9
                                     .orTimeout(3, TimeUnit.SECONDS);
            priceFutures.add(futurePriceInUSD);
        }
        // Drawback: The shop is not accessible anymore outside the loop,
        // so the getName() call below has been commented out.
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .map(price -> /*shop.getName() +*/ " price is " + price)
                           .collect(toList());
    }
}

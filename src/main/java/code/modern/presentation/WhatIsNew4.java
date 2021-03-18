package code.modern.presentation;

import static code.modern.future.simple.ExchangeService.Money.EUR;
import static code.modern.future.simple.ExchangeService.Money.USD;
import static code.modern.future.simple.ExchangeService.getRate;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import code.modern.future.simple.ExchangeService;
import code.modern.future.simple.Shop;

public class WhatIsNew4 {
    private static final List<Shop> shops = List.of(new Shop("BestPrice"),
                                                    new Shop("LetsSaveBig"),
                                                    new Shop("MyFavoriteShop"),
                                                    new Shop("BuyItAll"),
                                                    new Shop("Amaton"),
                                                    new Shop("Okko"));

    public static List<String> findPricesInUSD(final String product) {
        final List<CompletableFuture<Double>> priceFutures = new ArrayList<>();

        for (final Shop shop : shops) {
            final CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                                     .thenCombine(
                                             CompletableFuture.supplyAsync(
                                                     () -> getRate(EUR, USD))
                                                              .completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS),
                                             (price, rate) -> price * rate
                                     )
                                     .orTimeout(3, TimeUnit.SECONDS);
            priceFutures.add(futurePriceInUSD);
        }

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .map(price -> " price is " + price)
                           .collect(toList());
    }
}

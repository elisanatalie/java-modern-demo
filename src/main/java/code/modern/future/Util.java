package code.modern.future;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Util {
    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        final int delay = 1000;
        //int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void randomDelay() {
        final int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(final double number) {
        synchronized (formatter) {
            return Double.parseDouble(formatter.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(final List<CompletableFuture<T>> futures) {
/*
    CompletableFuture<Void> allDoneFuture =
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    return allDoneFuture.thenApply(v ->
        futures.stream()
            .map(future -> future.join())
            .collect(Collectors.<T>toList())
    );
*/
        return CompletableFuture.supplyAsync(() -> futures.stream()
                                                          .map(CompletableFuture::join)
                                                          .collect(Collectors.<T>toList()));
    }
}

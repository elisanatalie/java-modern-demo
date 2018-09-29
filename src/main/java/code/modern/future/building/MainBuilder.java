package code.modern.future.building;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainBuilder {
    private static final ExecutorService executor = Executors.newFixedThreadPool(100, (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(final String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        final long start1 = System.nanoTime();
        final Car.Builder builder = Car.builder();

//        builder.engine(Factories.createEngine());
//        builder.wheel(Factories.createWheel());
//        builder.frame(Factories.createFrame());

        final List<Callable<Car.Builder>> callables = createFutures(builder);
        final List<Future<Car.Builder>> futures = executor.invokeAll(callables);

        for (final Future<Car.Builder> future : futures) {
            future.get(5, TimeUnit.MINUTES);
        }

        builder.build();

        final long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");
    }

    private static List<Callable<Car.Builder>> createFutures(final Car.Builder builder) {
        return List.of(() -> builder.engine(Factories.createEngine()),
                       () -> builder.wheel(Factories.createWheel()),
                       () -> builder.frame(Factories.createFrame()));
    }
}

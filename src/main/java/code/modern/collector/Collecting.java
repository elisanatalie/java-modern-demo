package code.modern.collector;

import java.util.Set;
import java.util.stream.IntStream;

public class Collecting {
    public static void main(final String[] args) {
        System.out.println("Custom collector to List: " + Set.of(3, 1, 2).stream().collect(ToListCollector.toListCollector()));
        System.out.println("Custom collector prime number: " + IntStream.rangeClosed(2, 100)
                                                                        .boxed()
                                                                        .collect(PrimeNumbersCollector.primeNumbersCollector()));
    }
}

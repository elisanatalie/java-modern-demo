package code.modern.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinUser {

    public static void main(final String[] args) {
        final long[] numbers = LongStream.rangeClosed(1, 1_000_000).toArray();
        final ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        final Long result = new ForkJoinPool().invoke(task);

        System.out.println("Sum of one million =" + result);
    }
}

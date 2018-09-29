package code.modern.parallel;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private static final long serialVersionUID = -9138488550173928928L;

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(final long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(final long[] numbers, final int start, final int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        final int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + (length / 2));
        leftTask.fork();
        final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + (length / 2), end);
        final Long rightResult = rightTask.compute();
        final Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

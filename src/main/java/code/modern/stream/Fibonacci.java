package code.modern.stream;

import java.util.stream.Stream;

public class Fibonacci {
    public static void main(final String[] args) {
        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
              .limit(20)
              .forEach(t -> System.out.println("(" + t[0] + ',' + t[1] + ')'));
    }
}

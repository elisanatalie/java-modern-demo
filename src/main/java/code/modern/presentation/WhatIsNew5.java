package code.modern.presentation;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WhatIsNew5 {
    public static void main(String[] args) {
        Stream<String> homeValueStream = Stream.ofNullable(System.getProperty("home"));

        System.out.println(homeValueStream);

        IntStream.iterate(0, n -> n + 4)
                 .filter(n -> n < 100)
                 .forEach(System.out::println);
    }
}

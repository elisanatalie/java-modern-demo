package code.modern.stream;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class Practice {
    public static void main(final String... args) {
        final Trader raoul = new Trader("Raoul", "Cambridge");
        final Trader mario = new Trader("Mario", "Milan");
        final Trader alan = new Trader("Alan", "Cambridge");
        final Trader brian = new Trader("Brian", "Cambridge");

        final List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        final List<Transaction> result1 = transactions.stream()
                                                      .filter(t -> t.getYear() == 2011)
                                                      .sorted(Comparator.comparing(Transaction::getValue))
                                                      .collect(Collectors.toList());

        System.out.println(result1);

        // Query 2: What are all the unique cities where the traders work?
        final Set<String> result2 = transactions.stream()
                                                .map(t -> t.getTrader().getCity())
                                                .collect(Collectors.toSet());
        System.out.println(result2);

        // Query 3: Find all traders from Cambridge and sort them by name.
        final List<Trader> result3 = transactions.stream()
                                                 .map(Transaction::getTrader)
                                                 .filter(t -> "Cambridge".equals(t.getCity()))
                                                 .distinct()
                                                 .sorted(Comparator.comparing(Trader::getName))
                                                 .collect(Collectors.toList());

        System.out.println(result3);

        // Query 4: Return a string of all traders' names sorted alphabetically.
        final String result4 = transactions.stream()
                                           .map(t -> t.getTrader().getName())
                                           .distinct()
                                           .sorted()
                                           .collect(joining());
        System.out.println(result4);

        // Query 5: Are there any trader based in Milan?
        final boolean result5 = transactions.stream()
                                            .map(t -> t.getTrader().getCity())
                                            .anyMatch("Milan"::equals);
        System.out.println(result5);

        // Query 6: Print all transactions' values from the traders living in Cambridge.
        transactions.stream()
                    .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                    .map(Transaction::getValue)
                    .forEach(System.out::println);

        // Query 7: What's the highest value of all the transactions?
        final OptionalInt result7 = transactions.stream()
                                                .mapToInt(Transaction::getValue)
                                                .max();
        System.out.println(result7);

        // Query 8: Find the transaction with the smallest value
        final Optional<Transaction> result8 = transactions.stream()
                                                          .min(Comparator.comparing(Transaction::getValue));
        System.out.println(result8);
    }
}

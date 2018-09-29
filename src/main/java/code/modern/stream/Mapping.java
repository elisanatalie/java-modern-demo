package code.modern.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Mapping {
    private static final String[] arrayOfWords = {"Hello", "World"};
    private static final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> numbers2 = Arrays.asList(3, 4);

    public static List<String> getUniqueCharacters() {
        final Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        return streamOfwords.map(word -> word.split(""))
                            .flatMap(Arrays::stream)
                            .distinct()
                            .collect(toList());
    }

    public static List<int[]> getAllPairs() {
        return numbers1.stream()
                       .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                       .collect(toList());
    }

    public static List<int[]> getDivisibleByThreePairs() {
        return numbers1.stream()
                       .flatMap(i -> numbers2.stream().filter(j -> ((i + j) % 3) == 0).map(j -> new int[]{i, j}))
                       .collect(toList());
    }
}

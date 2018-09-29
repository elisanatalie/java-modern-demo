package code.modern.lambda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComposingFunctions {
    private static final Function<Integer, Integer> f = x -> x + 1;
    private static final Function<Integer, Integer> g = x -> x * 2;

    public static List<Integer> applyAndThen(final List<Integer> input) {
        return input.stream()
                    .map(f.andThen(g))
                    .collect(Collectors.toList());
    }

    public static List<Integer> applyCompose(final List<Integer> input) {
        return input.stream()
                    .map(f.compose(g))
                    .collect(Collectors.toList());
    }
}

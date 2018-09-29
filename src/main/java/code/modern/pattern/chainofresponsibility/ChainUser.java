package code.modern.pattern.chainofresponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainUser {
    public static void main(final String[] args) {
        final UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        final UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        final Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        final String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }
}

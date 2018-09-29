package code.modern.pattern.strategy;

import java.util.function.Predicate;

public class Validator {
    private final Predicate<String> strategy;

    public Validator(final Predicate<String> strategy) {
        this.strategy = strategy;
    }

    public boolean validate(final String s) {
        return strategy.test(s);
    }
}


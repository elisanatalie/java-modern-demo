package code.modern.pattern.strategy;

public class ValidatorUser {
    public static void main(final String[] args) {
        final Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(numericValidator.validate("aaaa"));

        final Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
        System.out.println(lowerCaseValidator.validate("bbbb"));
    }
}

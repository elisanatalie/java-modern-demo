package code.modern.lambda;

import static java.util.Comparator.comparing;

import java.util.List;

public class FruitSorter {

    public static List<Apple> getSorted(final List<Apple> inventory) {
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        return inventory;
    }
}

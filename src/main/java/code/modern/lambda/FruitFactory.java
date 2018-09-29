package code.modern.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class FruitFactory {
    private static final Map<String, BiFunction<Integer, Color, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(final String fruit, final Integer weight, final Color color) {
        return map.get(fruit.toLowerCase()).apply(weight, color);
    }
}

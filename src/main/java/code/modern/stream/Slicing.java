package code.modern.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class Slicing {
    private static final List<Dish> specialMenu = Arrays.asList(new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                                                                new Dish("prawns", false, 300, Dish.Type.FISH),
                                                                new Dish("rice", true, 350, Dish.Type.OTHER),
                                                                new Dish("chicken", false, 400, Dish.Type.MEAT),
                                                                new Dish("french fries", true, 530, Dish.Type.OTHER),
                                                                new Dish("doughnut", true, 600, Dish.Type.OTHER));

    public static List<Dish> getLowCalories() {
        return specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());
    }

    public static List<Dish> getHighCalories() {
        return specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());
    }

    public static List<Dish> getThreeHighCalories() {
        return specialMenu.stream().filter(dish -> dish.getCalories() > 300).limit(3).collect(toList());
    }

    public static List<Dish> getSkipOneHighCalories() {
        return specialMenu.stream().filter(dish -> dish.getCalories() > 300).skip(1).collect(toList());
    }
}



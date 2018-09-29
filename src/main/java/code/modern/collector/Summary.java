package code.modern.collector;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.summarizingInt;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import code.modern.stream.Dish;

public class Summary {
    public static void main(final String[] args) {
        final List<Dish> menu = Dish.menu;

        System.out.println("We have " + menu.stream().count() + " dishes");

        final Optional<Dish> mostCalorieDish = menu.stream().max(comparingInt(Dish::getCalories));
        System.out.println("We have most caloric dish " + mostCalorieDish.orElse(null) + " dishes");

        final IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("Statistics: " + menuStatistics);

        final int totalCalories1 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).orElse(0);
        final int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();

        System.out.println("Total calories is: " + totalCalories1 + " or " + totalCalories2);
    }
}

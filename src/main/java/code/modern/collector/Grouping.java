package code.modern.collector;

import static java.util.Comparator.comparingInt;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import code.modern.stream.Dish;

public class Grouping {
    public static void main(final String[] args) {
        final List<Dish> menu = Dish.menu;
        final Map<String, List<String>> dishTags = Dish.dishTags;

        final Map<Dish.Type, List<Dish>> caloricDishesByType1 = menu.stream()
                                                                    .filter(dish -> dish.getCalories() > 500)
                                                                    .collect(groupingBy(Dish::getType));

        final Map<Dish.Type, List<Dish>> caloricDishesByType2 = menu.stream()
                                                                    .collect(groupingBy(Dish::getType,
                                                                                        filtering(dish -> dish.getCalories() > 500,
                                                                                                  toList())));

        System.out.println("Filter then group: " + caloricDishesByType1);
        System.out.println("Group then filter: " + caloricDishesByType2);

        final Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                                                                 .collect(groupingBy(Dish::getType,
                                                                                     mapping(Dish::getName, toList())));

        System.out.println("Dish names by type: " + dishNamesByType);

        final Map<Dish.Type, Set<String>> dishTagsByType = menu.stream()
                                                               .collect(groupingBy(Dish::getType,
                                                                                   flatMapping(
                                                                                           dish -> dishTags.get(dish.getName()).stream(),
                                                                                           toSet())));

        System.out.println("Dish tags by type: " + dishTagsByType);

        final Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                                                           .collect(toMap(Dish::getType,
                                                                          identity(),
                                                                          maxBy(comparingInt(Dish::getCalories))));

        System.out.println("Most caloric by type: " + mostCaloricByType);
    }
}

package code.modern.presentation;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import code.modern.stream.Dish;

public class WhatIsNew1 {
    private static final List<Dish> specialMenu = Arrays.asList(new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                                                                new Dish("prawns", false, 300, Dish.Type.FISH),
                                                                new Dish("rice", true, 350, Dish.Type.OTHER),
                                                                new Dish("chicken", false, 400, Dish.Type.MEAT),
                                                                new Dish("french fries", true, 530, Dish.Type.OTHER));

    public static void main(final String[] args) {
        final List<Dish> slicedMenu1 = specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());

        System.out.println(slicedMenu1);

        List<Dish> slicedMenu2 = specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());

        System.out.println(slicedMenu2);
    }
}

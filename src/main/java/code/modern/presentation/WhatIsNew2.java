package code.modern.presentation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class WhatIsNew2 {
    public static void main(final String[] args) {
        final List<String> friends1 = List.of("Raphael", "Olivia", "Thibaut");
        // friends1.add("Monica");

        System.out.println(friends1);

        final Set<String> friends2 = Set.of("Raphael", "Olivia", "Thibaut");

        System.out.println(friends2);

        final Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

        System.out.println(ageOfFriends);
    }
}

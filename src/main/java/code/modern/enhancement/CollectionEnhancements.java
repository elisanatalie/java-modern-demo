package code.modern.enhancement;

import static java.util.Map.entry;

import java.util.HashMap;
import java.util.Map;

public class CollectionEnhancements {
    public static void main(final String[] args) {
        final Map<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

        System.out.println("Movies: " + movies);
        movies.entrySet().removeIf(entry -> entry.getValue() < 10);
        System.out.println("Result after remove if: " + movies);

        final Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Julia", "Star Wars");
        favouriteMovies.put("Olivia", "james bond");
        favouriteMovies.put("Raphael", "Jack Reacher 2");

        System.out.println("Favourite movies: ");
        favouriteMovies.entrySet()
                       .stream()
                       .sorted(Map.Entry.comparingByKey())
                       .forEachOrdered(System.out::println);

        favouriteMovies.remove("Raphael", "Jack Reacher 2");
        System.out.println("Result after remove: " + favouriteMovies);

        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println("Result after replace all: " + favouriteMovies);

        final Map<String, String> family = Map.ofEntries(entry("Teo", "Star Wars"), entry("Cristina", "James Bond"));
        final Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));
        final Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println("Family: " + family);
        System.out.println("Friends: " + friends);
        System.out.println("Result after merge: " + everyone);
    }
}

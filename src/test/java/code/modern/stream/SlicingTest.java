package code.modern.stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class SlicingTest {

    @Test
    void getLowCalories() {
        final List<String> actual = Slicing.getLowCalories().stream().map(Dish::getName).collect(Collectors.toList());
        assertThat(actual).containsExactly("seasonal fruit", "prawns");
    }

    @Test
    void getHighCalories() {
        final List<String> actual = Slicing.getHighCalories().stream().map(Dish::getName).collect(Collectors.toList());
        assertThat(actual).containsExactly("rice", "chicken", "french fries", "doughnut");
    }

    @Test
    void getThreeHighCalories() {
        final List<String> actual = Slicing.getThreeHighCalories().stream().map(Dish::getName).collect(Collectors.toList());
        assertThat(actual).containsExactly("rice", "chicken", "french fries");
    }

    @Test
    void getSkipOneHighCalories() {
        final List<String> actual = Slicing.getSkipOneHighCalories().stream().map(Dish::getName).collect(Collectors.toList());
        assertThat(actual).containsExactly("chicken", "french fries", "doughnut");
    }
}
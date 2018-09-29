package code.modern.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FruitSorterTest {
    private static final List<Apple> inventory = new ArrayList<>();

    static {
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.RED),
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        ));
    }

    @Test
    void getSorted() {
        final List<Apple> apples = FruitSorter.getSorted(inventory);

        assertThat(apples.get(0).getWeight()).isEqualTo(155);
        assertThat(apples.get(0).getColor()).isEqualTo(Color.GREEN);
        assertThat(apples.get(3).getWeight()).isEqualTo(80);
        assertThat(apples.get(3).getColor()).isEqualTo(Color.GREEN);
    }
}
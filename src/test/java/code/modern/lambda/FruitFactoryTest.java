package code.modern.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FruitFactoryTest {

    @Test
    void giveMeFruit() {
        final Fruit apple = FruitFactory.giveMeFruit("Apple", 100, Color.GREEN);
        assertThat(apple).isInstanceOf(Apple.class);
        assertThat(apple.getWeight()).isEqualTo(100);
        assertThat(apple.getColor()).isEqualTo(Color.GREEN);

        final Fruit orange = FruitFactory.giveMeFruit("Orange", 200, Color.ORANGE);
        assertThat(orange).isInstanceOf(Orange.class);
        assertThat(orange.getWeight()).isEqualTo(200);
        assertThat(orange.getColor()).isEqualTo(Color.ORANGE);
    }
}
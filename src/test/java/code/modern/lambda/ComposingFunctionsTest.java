package code.modern.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class ComposingFunctionsTest {
    private final List<Integer> input = List.of(1, 2, 3);

    @Test
    void applyAndThen() {
        assertThat(ComposingFunctions.applyAndThen(input)).containsExactly(4, 6, 8);
    }

    @Test
    void applyCompose() {
        assertThat(ComposingFunctions.applyCompose(input)).containsExactly(3, 5, 7);
    }
}
package code.modern.stream;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MappingTest {

    @Test
    void getUniqueCharacters() {
        assertThat(Mapping.getUniqueCharacters()).containsExactly("H", "e", "l", "o", "W", "r", "d");
    }

    @Test
    void getAllPairs() {
        assertThat(Mapping.getAllPairs()).containsExactly(new int[]{1, 3},
                                                          new int[]{1, 4},
                                                          new int[]{2, 3},
                                                          new int[]{2, 4},
                                                          new int[]{3, 3},
                                                          new int[]{3, 4});
    }

    @Test
    void getDivisibleByThreePairs() {
        assertThat(Mapping.getDivisibleByThreePairs()).containsExactly(new int[]{2, 4},
                                                                       new int[]{3, 3});
    }
}
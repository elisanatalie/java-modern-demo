package code.clean.init;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestClassTwoTest extends TestMainClass {
    @Test
    public void testTwo() {
        assertThat("Test").isEqualTo("Test");
    }
}

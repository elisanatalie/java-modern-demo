package code.clean.init;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestClassOneTest extends TestMainClass {
    @Test
    public void testOne() {
        assertThat("Test2").isEqualTo("Test2");
    }
}

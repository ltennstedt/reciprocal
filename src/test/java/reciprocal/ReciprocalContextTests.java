package reciprocal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

final class ReciprocalContextTests {
    @Test
    void constructor_should_throw_Exception_when_mathContext_is_null() {
        assertThatCode(() -> new ReciprocalContext(0, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("mathContext");
    }
}

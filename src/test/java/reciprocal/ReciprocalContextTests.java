package reciprocal;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class ReciprocalContextTests {
    @Test
    void constructor_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new ReciprocalContext(0, null)).withMessage("mathContext")
            .withNoCause();
    }
}

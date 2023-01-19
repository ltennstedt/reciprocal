package reciprocal.precondition;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static reciprocal.precondition.RectanglePreconditions.checkLength;
import static reciprocal.precondition.RectanglePreconditions.checkWidth;

import org.junit.jupiter.api.Test;

final class RectanglePreconditionsTests {
    @Test
    void checkLength_should_throw_Exception_when_length_is_null() {
        assertThatThrownBy(() -> checkLength(true, null))
            .isExactlyInstanceOf(NullPointerException.class).hasMessage("length").hasNoCause();
    }

    @Test
    void checkLength_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkLength(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("length > 0 expected but length = 0")
            .hasNoCause();
    }

    @Test
    void checkLength_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkLength(true, 1)).doesNotThrowAnyException();
    }

    @Test
    void checkWidth_should_throw_Exception_when_length_is_null() {
        assertThatThrownBy(() -> checkWidth(true, null))
            .isExactlyInstanceOf(NullPointerException.class).hasMessage("width").hasNoCause();
    }

    @Test
    void checkWidth_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkWidth(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("width > 0 expected but width = 0")
            .hasNoCause();
    }

    @Test
    void checkWidth_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkWidth(true, 1)).doesNotThrowAnyException();
    }
}

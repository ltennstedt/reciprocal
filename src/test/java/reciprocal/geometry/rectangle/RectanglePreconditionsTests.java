package reciprocal.geometry.rectangle;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewWidth;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkWidth;

import org.junit.jupiter.api.Test;

final class RectanglePreconditionsTests {
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
    void checkWidth_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkWidth(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("width > 0 expected but width = 0")
            .hasNoCause();
    }

    @Test
    void checkWidth_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkWidth(true, 1)).doesNotThrowAnyException();
    }

    @Test
    void checkNewLength_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkNewLength(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("newLength > 0 expected but newLength = 0")
            .hasNoCause();
    }

    @Test
    void checkNewLength_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkNewLength(true, 1)).doesNotThrowAnyException();
    }

    @Test
    void checkNewWidth_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkNewWidth(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("newWidth > 0 expected but newWidth = 0")
            .hasNoCause();
    }

    @Test
    void checkNewWidth_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkNewWidth(true, 1)).doesNotThrowAnyException();
    }
}

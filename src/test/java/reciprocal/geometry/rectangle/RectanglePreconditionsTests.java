package reciprocal.geometry.rectangle;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewWidth;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkWidth;

import org.junit.jupiter.api.Test;

final class RectanglePreconditionsTests {
    @Test
    void checkLength_should_throw_Exception_when_b_is_false() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkLength(false, 0))
                .withMessage("length > 0 expected but length = 0").withNoCause();
    }

    @Test
    void checkLength_should_not_throw_Exception_when_b_is_true() {
        assertThatNoException().isThrownBy(() -> checkLength(true, 1));
    }

    @Test
    void checkWidth_should_throw_Exception_when_b_is_false() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkWidth(false, 0))
                .withMessage("width > 0 expected but width = 0").withNoCause();
    }

    @Test
    void checkWidth_should_not_throw_Exception_when_b_is_true() {
        assertThatNoException().isThrownBy(() -> checkWidth(true, 1));
    }

    @Test
    void checkNewLength_should_throw_Exception_when_b_is_false() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkNewLength(false, 0))
                .withMessage("newLength > 0 expected but newLength = 0").withNoCause();
    }

    @Test
    void checkNewLength_should_not_throw_Exception_when_b_is_true() {
        assertThatNoException().isThrownBy(() -> checkNewLength(true, 1));
    }

    @Test
    void checkNewWidth_should_throw_Exception_when_b_is_false() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkNewWidth(false, 0))
                .withMessage("newWidth > 0 expected but newWidth = 0").withNoCause();
    }

    @Test
    void checkNewWidth_should_not_throw_Exception_when_b_is_true() {
        assertThatNoException().isThrownBy(() -> checkNewWidth(true, 1));
    }
}

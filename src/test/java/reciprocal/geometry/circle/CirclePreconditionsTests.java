package reciprocal.geometry.circle;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static reciprocal.geometry.circle.CirclePreconditions.checkRadius;

import org.junit.jupiter.api.Test;

final class CirclePreconditionsTests {
    @Test
    void checkRadius_should_throw_Exception_when_b_is_false() {
        assertThatThrownBy(() -> checkRadius(false, 0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("radius > 0 expected but radius = 0")
            .hasNoCause();
    }

    @Test
    void checkRadius_should_not_throw_Exception_when_b_is_true() {
        assertThatCode(() -> checkRadius(true, 1)).doesNotThrowAnyException();
    }
}

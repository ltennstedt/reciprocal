package reciprocal.geometry.circle;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static reciprocal.geometry.circle.CirclePreconditions.checkRadius;

import org.junit.jupiter.api.Test;

final class CirclePreconditionsTests {
    @Test
    void checkRadius_should_throw_Exception_when_b_is_false() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkRadius(false, 0))
            .withMessage("radius > 0 expected but radius = 0").withNoCause();
    }

    @Test
    void checkRadius_should_not_throw_Exception_when_b_is_true() {
        assertThatNoException().isThrownBy(() -> checkRadius(true, 1));
    }
}

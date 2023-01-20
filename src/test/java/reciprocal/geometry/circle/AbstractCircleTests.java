package reciprocal.geometry.circle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

class AbstractCircleTests {
    @Test
    void constructor_should_throw_Exception_when_radius_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigCircle(null)).withMessage("radius").withNoCause();
    }

    @Test
    void hashCode_should_return_useful_hashCode() {
        assertThat(new Circle(1).hashCode()).isNotZero();
    }

    @Test
    void equals_should_return_true_when_this_is_same_as_obj() {
        final var circle = new Circle(1);

        assertThat(circle.equals(circle)).isTrue();
    }
}

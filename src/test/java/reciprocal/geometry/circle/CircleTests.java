package reciprocal.geometry.circle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reciprocal.geometry.circle.Circle.CircleComparator;

final class CircleTests {
    @Test
    void getDiameter_should_return_diameter() {
        assertThat(new Circle(0.5D).getDiameter()).isOne();
    }

    @Test
    void getCircumference_should_return_circumference() {
        assertThat(new Circle(1.0D).getCircumference()).isEqualByComparingTo(6.283185307179586D);
    }

    @Test
    void getArea_should_return_area() {
        assertThat(new Circle(1.0D).getArea()).isEqualByComparingTo(3.141592653589793D);
    }

    @Test
    void compareTo_should_throw_Exception_when_o_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new Circle(1.0D).compareTo(null)).withMessage("o")
            .withNoCause();
    }

    @Test
    void compareTo_should_return_minus_1_when_this_is_less_than_o() {
        assertThat(new Circle(1.0D).compareTo(new Circle(2.0D))).isSameAs(-1);
    }

    @Test
    void compareTo_should_return_0_when_this_is_equal_to_o() {
        assertThat(new Circle(1.0D)).isEqualByComparingTo(new Circle(1.0D));
    }

    @Test
    void compareTo_should_return_1_when_this_is_greater_than_o() {
        assertThat(new Circle(2.0D).compareTo(new Circle(1.0D))).isOne();
    }

    @Nested
    final class CircleComparatorTests {
        @Test
        void compare_should_throw_Exception_when_o1_is_null() {
            assertThatNullPointerException().isThrownBy(() -> CircleComparator.INSTANCE.compare(null, null))
                .withMessage("o1").withNoCause();
        }

        @Test
        void compare_should_throw_Exception_when_o2_is_null() {
            assertThatNullPointerException().isThrownBy(() -> CircleComparator.INSTANCE.compare(new Circle(1.0D), null))
                .withMessage("o2").withNoCause();
        }
    }
}

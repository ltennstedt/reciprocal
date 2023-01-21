package reciprocal.geometry.circle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reciprocal.geometry.circle.BigCircle.BigCircleComparator;

final class BigCircleTests {
    @Test
    void constructor_should_throw_Exception_when_radius_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigCircle(null)).withMessage("radius").withNoCause();
    }

    @Test
    void getDiameter_should_return_diameter() {
        assertThat(new BigCircle(new BigDecimal("0.5")).getDiameter()).isOne();
    }

    @Test
    void getCircumference_should_return_circumference() {
        assertThat(new BigCircle(BigDecimal.ONE).getCircumference())
                .isEqualByComparingTo(new BigDecimal("6.283185307179586"));
    }

    @Test
    void getArea_should_return_area() {
        assertThat(new BigCircle(BigDecimal.ONE).getArea())
                .isEqualByComparingTo(new BigDecimal("3.141592653589793"));
    }

    @Test
    void compareTo_should_throw_Exception_when_o_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigCircle(BigDecimal.ONE).compareTo(null))
                .withMessage("o").withNoCause();
    }

    @Test
    void compareTo_should_return_minus_1_when_this_is_less_than_o() {
        assertThat(new BigCircle(BigDecimal.ONE).compareTo(new BigCircle(BigDecimal.TEN))).isSameAs(-1);
    }

    @Test
    void compareTo_should_return_0_when_this_is_equal_to_o() {
        assertThat(new BigCircle(BigDecimal.ONE)).isEqualByComparingTo(new BigCircle(BigDecimal.ONE));
    }

    @Test
    void compareTo_should_return_1_when_this_is_greater_than_o() {
        assertThat(new BigCircle(BigDecimal.TEN).compareTo(new BigCircle(BigDecimal.ONE))).isOne();
    }

    @Nested
    final class BigCircleComparatorTests {
        @Test
        void compare_should_throw_Exception_when_o1_is_null() {
            assertThatNullPointerException().isThrownBy(() -> BigCircleComparator.INSTANCE.compare(null, null))
                    .withMessage("o1").withNoCause();
        }

        @Test
        void compare_should_throw_Exception_when_o2_is_null() {
            assertThatNullPointerException()
                    .isThrownBy(() -> BigCircleComparator.INSTANCE.compare(new BigCircle(BigDecimal.ONE), null))
                    .withMessage("o2")
                    .withNoCause();
        }
    }
}

package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

final class AbstractComplexTests {
    @Test
    void constructor_should_throw_Exception_when_real_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigGaussian(null, null)).withMessage("real")
            .withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_imaginary_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigGaussian(BigInteger.ZERO, null))
            .withMessage("imaginary").withNoCause();
    }

    @Test
    void isNotInvertible_should_return_false_when_Gaussian_is_invertible() {
        assertThat(Gaussian.ONE.isNotInvertible()).isFalse();
    }

    @Test
    void isNotInvertible_should_return_true_when_Gaussian_is_not_invertible() {
        assertThat(new Gaussian(1L, 1L).isNotInvertible()).isTrue();
    }

    @Test
    void intValue_should_return_this_as_int() {
        assertThat(Gaussian.ZERO.intValue()).isZero();
    }

    @Test
    void intValue_should_return_this_as_long() {
        assertThat(Gaussian.ZERO.longValue()).isZero();
    }

    @Test
    void intValue_should_return_this_as_float() {
        assertThat(Gaussian.ZERO.floatValue()).isZero();
    }

    @Test
    void intValue_should_return_this_as_double() {
        assertThat(Gaussian.ZERO.doubleValue()).isZero();
    }

    @Test
    void plus_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.plus(null)).withMessage("summand")
            .withNoCause();
    }

    @Test
    void plus_should_calculate_and_return_sum() {
        assertThat(Gaussian.ONE.plus(Gaussian.IMAGINARY)).isEqualTo(new Gaussian(1L, 1L));
    }
}

package reciprocal.number.complex;

import static java.util.Objects.hash;
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
        assertThat(Gaussian.ONE.plus(Gaussian.I)).isEqualTo(new Gaussian(1L, 1L));
    }

    @Test
    void minus_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.minus(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void minus_should_calculate_and_return_difference() {
        assertThat(Gaussian.ONE.minus(Gaussian.I)).isEqualTo(new Gaussian(1L, -1L));
    }

    @Test
    void div_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.div(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void div_should_calculate_and_return_quotient() {
        assertThat(Gaussian.ONE.div(Gaussian.I)).isEqualTo(Complex.MINUS_I);
    }

    @Test
    void power_should_calculate_and_return_power() {
        assertThat(new Gaussian(1L, 1L).power(2)).isEqualTo(Complex.ofImaginary(2.0D));
    }

    @Test
    void hashCode_should_return_hashCode() {
        assertThat(new Gaussian(1L, 2L).hashCode()).isEqualByComparingTo(hash(1L, 2L));
    }

    @Test
    void equals_should_return_true_when_this_is_same_as_obj() {
        assertThat(Gaussian.ZERO.equals(Gaussian.ZERO)).isTrue();
    }

    @Test
    void equals_should_return_false_when_obj_is_null() {
        assertThat(Gaussian.ZERO.equals(null)).isFalse();
    }

    @Test
    void equals_should_return_false_when_classes_are_not_the_same() {
        assertThat(Gaussian.ZERO.equals(new Object())).isFalse();
    }

    @Test
    void equals_should_return_false_when_real_parts_are_unequal() {
        assertThat(Gaussian.ZERO.equals(Gaussian.ONE)).isFalse();
    }

    @Test
    void equals_should_return_false_when_imaginary_parts_are_unequal() {
        assertThat(Gaussian.ZERO.equals(Gaussian.I)).isFalse();
    }

    @Test
    void equals_should_return_true_when_real_and_imaginary_parts_are_equal() {
        assertThat(Gaussian.ONE.equals(Gaussian.ofReal(1L))).isTrue();
    }

    @Test
    void toString_should_return_toString() {
        assertThat(Gaussian.ONE).hasToString("Gaussian{real=1, imaginary=0}");
    }
}

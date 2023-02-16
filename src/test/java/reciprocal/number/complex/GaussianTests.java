package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class GaussianTests {
    @Test
    void ofReal_should_return_instance() {
        final var actual = Gaussian.ofReal(1L);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void ofImaginary_should_return_instance() {
        final var actual = Gaussian.ofImaginary(1L);

        assertThat(actual.getReal()).isZero();
        assertThat(actual.getImaginary()).isOne();
    }

    @Test
    void isInvertible_should_return_false_when_this_is_not_invertible() {
        assertThat(Gaussian.ZERO.isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_this_is_invertible() {
        assertThat(Gaussian.ONE.isInvertible()).isTrue();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.add(null)).withMessage("summand")
            .withNoCause();
    }

    @Test
    void add_should_calculate_sum() {
        final var gaussian = new Gaussian(1L, 2L);
        final var summand = new Gaussian(3L, 4L);

        final var actual = gaussian.add(summand);

        assertThat(actual.getReal()).isEqualByComparingTo(4L);
        assertThat(actual.getImaginary()).isEqualByComparingTo(6L);
    }

    @Test
    void subtract_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_calculate_difference() {
        final var Gaussian = new Gaussian(1L, 2L);
        final var subtrahend = new Gaussian(3L, -4L);

        final var actual = Gaussian.subtract(subtrahend);

        assertThat(actual.getReal()).isEqualByComparingTo(-2L);
        assertThat(actual.getImaginary()).isEqualByComparingTo(6L);
    }

    @Test
    void multiply_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.multiply(null)).withMessage("factor")
            .withNoCause();
    }

    @Test
    void multiply_should_calculate_product() {
        final var Gaussian = new Gaussian(1L, 2L);
        final var factor = new Gaussian(3L, 4L);

        final var actual = Gaussian.multiply(factor);

        assertThat(actual.getReal()).isEqualByComparingTo(-5L);
        assertThat(actual.getImaginary()).isEqualByComparingTo(10L);
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.divide(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void divide_should_calculate_quotient() {
        final var Gaussian = new Gaussian(1L, 2L);
        final var divisor = new Gaussian(3L, 4L);

        final var actual = Gaussian.divide(divisor);

        assertThat(actual.getReal()).isEqualByComparingTo(0.44D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(0.08D);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new Gaussian(1L, 2L).pow(-2);

        assertThat(actual.getReal()).isEqualByComparingTo(-0.12D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-0.16D);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_2() {
        final var actual = new Gaussian(1L, 2L).pow(2);

        assertThat(actual.getReal()).isEqualTo(-3L);
        assertThat(actual.getImaginary()).isEqualTo(4L);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_0() {
        final var actual = new Gaussian(1L, 2L).pow(0);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void negate_should_calculate_negated() {
        final var actual = new Gaussian(1L, 2L).negate();

        assertThat(actual.getReal()).isEqualByComparingTo(-1L);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-2L);
    }

    @Test
    void invert_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Gaussian.ZERO::invert)
            .withMessage("this expected to be invertible but this = Gaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void invert_should_calculate_inverted() {
        final var actual = new Gaussian(2L, 3L).invert();

        assertThat(actual.getReal()).isEqualByComparingTo(0.1538461538461538461538461538461538D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-0.2307692307692307692307692307692308D);
    }

    @Test
    void abs_should_calculate_absolute_value() {
        final var actual = new Gaussian(1L, 2L).abs();

        assertThat(actual).isEqualByComparingTo(2.236067977499789696409173668731276D);
    }

    @Test
    void conjugate_should_calculate_conjugate() {
        final var actual = new Gaussian(1L, 2L).conjugate();

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isEqualByComparingTo(-2L);
    }

    @Test
    void argument_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Gaussian.ZERO::argument)
            .withMessage("this expected to be invertible but this = Gaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void argument_should_return_minus_acos_when_imaginary_is_less_than_0() {
        final var actual = new Gaussian(1L, -1L).argument();

        assertThat(actual).isEqualByComparingTo(-0.7853981633974484D);
    }

    @Test
    void argument_should_return_acos_when_imaginary_is_greater_than_0() {
        final var actual = new Gaussian(1L, 1L).argument();

        assertThat(actual).isEqualByComparingTo(0.7853981633974484D);
    }

    @Test
    void toBigInteger_should_return_this_as_BigInteger() {
        assertThat(Gaussian.ONE.toBigInteger()).isOne();
    }

    @Test
    void toBigDecimal_should_return_this_as_BigDecimal() {
        assertThat(Gaussian.ONE.toBigDecimal()).isOne();
    }

    @Test
    void toPolarForm_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Gaussian.ZERO::toPolarForm)
            .withMessage("this expected to be invertible but this = Gaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void toPolarForm_should_calculate_PolarForm() {
        final var actual = new Gaussian(1L, 2L).toPolarForm();

        assertThat(actual.getRadial()).isEqualByComparingTo(2.236067977499789696409173668731276D);
        assertThat(actual.getAngular()).isEqualByComparingTo(1.107148717794090503017065460178537D);
    }

    @Test
    void equalsByComparing_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Gaussian.ZERO.equalsByComparing(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void equalsByComparing_should_return_false_when_real_parts_are_unequal_by_comparing() {
        assertThat(Gaussian.ZERO.equalsByComparing(Gaussian.ONE)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_false_when_imaginary_parts_are_unequal_by_comparing() {
        assertThat(Gaussian.ZERO.equalsByComparing(Gaussian.I)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_true_when_this_is_this_is_equal_by_comparing_to_other() {
        final var Gaussian = new Gaussian(1L, 2L);
        final var other = new Gaussian(1L, 2L);

        assertThat(Gaussian.equalsByComparing(other)).isTrue();
    }
}

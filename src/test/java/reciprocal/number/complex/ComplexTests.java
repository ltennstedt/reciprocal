package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class ComplexTests {
    @Test
    void ofReal_should_return_instance() {
        final var actual = Complex.ofReal(1.0D);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void ofImaginary_should_return_instance() {
        final var actual = Complex.ofImaginary(1.0D);

        assertThat(actual.getReal()).isZero();
        assertThat(actual.getImaginary()).isOne();
    }

    @Test
    void isInvertible_should_return_false_when_this_is_not_invertible() {
        assertThat(Complex.ZERO.isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_this_is_invertible() {
        assertThat(Complex.ONE.isInvertible()).isTrue();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Complex.ZERO.add(null)).withMessage("summand")
            .withNoCause();
    }

    @Test
    void add_should_calculate_sum() {
        final var complex = new Complex(1.0D, 2.0D);
        final var summand = new Complex(3.0D, 4.0D);

        final var actual = complex.add(summand);

        assertThat(actual.getReal()).isEqualByComparingTo(4.0D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(6.0D);
    }

    @Test
    void subtract_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Complex.ZERO.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_calculate_difference() {
        final var complex = new Complex(1.0D, 2.0D);
        final var subtrahend = new Complex(3.0D, -4.0D);

        final var actual = complex.subtract(subtrahend);

        assertThat(actual.getReal()).isEqualByComparingTo(-2.0D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(6.0D);
    }

    @Test
    void multiply_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Complex.ZERO.multiply(null)).withMessage("factor")
            .withNoCause();
    }

    @Test
    void multiply_should_calculate_product() {
        final var complex = new Complex(1.0D, 2.0D);
        final var factor = new Complex(3.0D, 4.0D);

        final var actual = complex.multiply(factor);

        assertThat(actual.getReal()).isEqualByComparingTo(-5.0D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(10.0D);
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Complex.ZERO.divide(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void divide_should_calculate_quotient() {
        final var complex = new Complex(1.0D, 2.0D);
        final var divisor = new Complex(3.0D, 4.0D);

        final var actual = complex.divide(divisor);

        assertThat(actual.getReal()).isEqualByComparingTo(0.44D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(0.08D);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new Complex(1.0D, 2.0D).pow(-2);

        assertThat(actual.getReal()).isEqualByComparingTo(-0.12D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-0.16D);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_2() {
        final var actual = new Complex(1.0D, 2.0D).pow(2);

        assertThat(actual.getReal()).isEqualByComparingTo(-3.0D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(4.0D);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_0() {
        final var actual = new Complex(1.0D, 2.0D).pow(0);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void negate_should_calculate_negated() {
        final var actual = new Complex(1.0D, 2.0D).negate();

        assertThat(actual.getReal()).isEqualByComparingTo(-1.0D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-2.0D);
    }

    @Test
    void invert_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Complex.ZERO::invert)
            .withMessage("this expected to be invertible but this = Complex{real=0.0, imaginary=0.0}").withNoCause();
    }

    @Test
    void invert_should_calculate_inverted() {
        final var actual = new Complex(2.0D, 3.0D).invert();

        assertThat(actual.getReal()).isEqualByComparingTo(0.1538461538461538461538461538461538D);
        assertThat(actual.getImaginary()).isEqualByComparingTo(-0.2307692307692307692307692307692308D);
    }

    @Test
    void abs_should_calculate_absolute_value() {
        final var actual = new Complex(1.0D, 2.0D).abs();

        assertThat(actual).isEqualByComparingTo(2.236067977499789696409173668731276D);
    }

    @Test
    void conjugate_should_calculate_conjugate() {
        final var actual = new Complex(1.0D, 2.0D).conjugate();

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isEqualByComparingTo(-2.0D);
    }

    @Test
    void argument_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Complex.ZERO::argument)
            .withMessage("this expected to be invertible but this = Complex{real=0.0, imaginary=0.0}").withNoCause();
    }

    @Test
    void argument_should_return_minus_acos_when_imaginary_is_less_than_0() {
        final var actual = new Complex(1.0D, -1.0D).argument();

        assertThat(actual).isEqualByComparingTo(-0.7853981633974484D);
    }

    @Test
    void argument_should_return_acos_when_imaginary_is_greater_than_0() {
        final var actual = new Complex(1.0D, 1.0D).argument();

        assertThat(actual).isEqualByComparingTo(0.7853981633974484D);
    }

    @Test
    void toBigInteger_should_return_this_as_BigInteger() {
        assertThat(Complex.ONE.toBigInteger()).isOne();
    }

    @Test
    void toBigIntegerExact_should_throw_Exception_when_real_is_not_an_exact_BigInteger() {
        assertThatException().isThrownBy(() -> Complex.ofReal(0.5D).toBigIntegerExact())
            .isExactlyInstanceOf(ArithmeticException.class);
    }

    @Test
    void toBigIntegerExact_should_return_this_as_exact_BigInteger() {
        assertThat(Complex.ONE.toBigIntegerExact()).isOne();
    }

    @Test
    void toBigDecimal_should_return_this_as_BigDecimal() {
        assertThat(Complex.ONE.toBigDecimal()).isOne();
    }

    @Test
    void toPolarForm_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(Complex.ZERO::toPolarForm)
            .withMessage("this expected to be invertible but this = Complex{real=0.0, imaginary=0.0}").withNoCause();
    }

    @Test
    void toPolarForm_should_calculate_PolarForm() {
        final var actual = new Complex(1.0D, 2.0D).toPolarForm();

        assertThat(actual.getRadial()).isEqualByComparingTo(2.236067977499789696409173668731276D);
        assertThat(actual.getAngular()).isEqualByComparingTo(1.107148717794090503017065460178537D);
    }

    @Test
    void equalsByComparing_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Complex.ZERO.equalsByComparing(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void equalsByComparing_should_return_false_when_real_parts_are_unequal_by_comparing() {
        assertThat(Complex.ZERO.equalsByComparing(Complex.ONE)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_false_when_imaginary_parts_are_unequal_by_comparing() {
        assertThat(Complex.ZERO.equalsByComparing(Complex.I)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_true_when_this_is_this_is_equal_by_comparing_to_other() {
        final var complex = new Complex(1.0D, 2.0D);
        final var other = new Complex(1.00D, 2.00D);

        assertThat(complex.equalsByComparing(other)).isTrue();
    }
}

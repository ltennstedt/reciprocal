package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.junit.jupiter.api.Test;

final class BigGaussianTests {
    @Test
    void ofReal_should_throw_Exception_when_real_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ofReal(null)).withMessage("real").withNoCause();
    }

    @Test
    void ofReal_should_return_instance() {
        final var actual = BigGaussian.ofReal(BigInteger.ONE);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void ofImaginary_should_throw_Exception_when_imaginary_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ofImaginary(null)).withMessage("imaginary")
            .withNoCause();
    }

    @Test
    void ofImaginary_should_return_instance() {
        final var actual = BigGaussian.ofImaginary(BigInteger.ONE);

        assertThat(actual.getReal()).isZero();
        assertThat(actual.getImaginary()).isOne();
    }

    @Test
    void isInvertible_should_return_false_when_this_is_not_invertible() {
        assertThat(BigGaussian.ZERO.isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_this_is_invertible() {
        assertThat(BigGaussian.ONE.isInvertible()).isTrue();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.add(null)).withMessage("summand")
            .withNoCause();
    }

    @Test
    void add_should_calculate_sum() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var summand = new BigGaussian(BigInteger.valueOf(3L), BigInteger.valueOf(4L));

        final var actual = complex.add(summand);

        assertThat(actual.getReal()).isEqualByComparingTo(BigInteger.valueOf(4L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigInteger.valueOf(6L));
    }

    @Test
    void subtract_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_calculate_difference() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var subtrahend = new BigGaussian(BigInteger.valueOf(3L), BigInteger.valueOf(-4L));

        final var actual = complex.subtract(subtrahend);

        assertThat(actual.getReal()).isEqualByComparingTo(BigInteger.valueOf(-2L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigInteger.valueOf(6L));
    }

    @Test
    void multiply_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.multiply(null)).withMessage("factor")
            .withNoCause();
    }

    @Test
    void multiply_should_calculate_product() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var factor = new BigGaussian(BigInteger.valueOf(3L), BigInteger.valueOf(4L));

        final var actual = complex.multiply(factor);

        assertThat(actual.getReal()).isEqualByComparingTo(BigInteger.valueOf(-5L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigInteger.TEN);
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.divide(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void divide_should_calculate_quotient() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var divisor = new BigGaussian(BigInteger.valueOf(3L), BigInteger.valueOf(4L));

        final var actual = complex.divide(divisor);

        assertThat(actual.getReal()).isEqualByComparingTo("0.44");
        assertThat(actual.getImaginary()).isEqualByComparingTo("0.08");
    }

    @Test
    void divide_with_MathContext_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.divide(null, MathContext.DECIMAL32))
            .withMessage("divisor").withNoCause();
    }

    @Test
    void divide_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.divide(BigGaussian.ONE, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void divide_with_MathContext_should_calculate_product() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var divisor = new BigGaussian(BigInteger.valueOf(3L), BigInteger.valueOf(4L));

        final var actual = complex.divide(divisor, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo("0.44");
        assertThat(actual.getImaginary()).isEqualByComparingTo("0.08");
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(-2);

        assertThat(actual.getReal()).isEqualByComparingTo("-0.12");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.16");
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_2() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(2);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-3L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(4));
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(0);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void pow_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.pow(0, null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(-2, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo("-0.12");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.16");
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_2() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(2, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-3L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(4));
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).pow(0, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void negate_should_calculate_negated() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).negate();

        assertThat(actual.getReal()).isEqualByComparingTo(BigInteger.ONE.negate());
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigInteger.valueOf(-2L));
    }

    @Test
    void invert_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(BigGaussian.ZERO::invert)
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void invert_should_calculate_inverted() {
        final var actual = new BigGaussian(BigInteger.valueOf(2L), BigInteger.valueOf(3L)).invert();

        assertThat(actual.getReal()).isEqualByComparingTo("0.1538461538461538461538461538461538");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.2307692307692307692307692307692308");
    }

    @Test
    void invert_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ONE.invert(null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void invert_with_MathContext_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(() -> BigGaussian.ZERO.invert(MathContext.DECIMAL32))
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void invert_with_MathContext_should_calculate_inverted() {
        final var actual =
            new BigGaussian(BigInteger.valueOf(2L), BigInteger.valueOf(3L)).invert(MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo("0.1538462");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.2307692");
    }

    @Test
    void abs_should_calculate_absolute_value() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).abs();

        assertThat(actual).isEqualByComparingTo("2.236067977499789696409173668731276");
    }

    @Test
    void abs_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.abs(null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void abs_with_MathContext_should_calculate_absolute_value() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).abs(MathContext.DECIMAL32);

        assertThat(actual).isEqualByComparingTo("2.236068");
    }

    @Test
    void conjugate_should_calculate_conjugate() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).conjugate();

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigInteger.valueOf(-2L));
    }

    @Test
    void argument_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(BigGaussian.ZERO::argument)
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void argument_should_return_minus_acos_when_imaginary_is_less_than_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.ONE.negate()).argument();

        assertThat(actual).isEqualByComparingTo("-0.7853981633974483096156608458198756");
    }

    @Test
    void argument_should_return_acos_when_imaginary_is_greater_than_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.ONE).argument();

        assertThat(actual).isEqualByComparingTo("0.7853981633974483096156608458198756");
    }

    @Test
    void argument_with_MathContext_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(() -> BigGaussian.ZERO.argument(MathContext.DECIMAL32))
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void argument_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ONE.argument(null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void argument_with_MathContext_should_return_minus_acos_when_imaginary_is_less_than_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.ONE.negate()).argument(MathContext.DECIMAL32);

        assertThat(actual).isEqualByComparingTo("-0.7853984");
    }

    @Test
    void argument_with_MathContext_should_return_acos_when_imaginary_is_greater_than_0() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.ONE).argument(MathContext.DECIMAL32);

        assertThat(actual).isEqualByComparingTo("0.7853984");
    }

    @Test
    void toBigInteger_should_return_this_as_BigInteger() {
        assertThat(BigGaussian.ONE.toBigInteger()).isOne();
    }

    @Test
    void toPolarForm_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(BigGaussian.ZERO::toPolarForm)
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void toPolarForm_should_calculate_PolarForm() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).toPolarForm();

        assertThat(actual.getRadial()).isEqualByComparingTo("2.236067977499789696409173668731276");
        assertThat(actual.getAngular()).isEqualByComparingTo("1.107148717794090503017065460178537");
    }

    @Test
    void toPolarForm_with_MathContext_should_throw_Exception_when_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(() -> BigGaussian.ZERO.toPolarForm(MathContext.DECIMAL32))
            .withMessage("this expected to be invertible but this = BigGaussian{real=0, imaginary=0}").withNoCause();
    }

    @Test
    void toPolarForm_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.toPolarForm(null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void toPolarForm_with_MathContext_should_calculate_PolarForm() {
        final var actual = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L)).toPolarForm(MathContext.DECIMAL32);

        assertThat(actual.getRadial()).isEqualByComparingTo("2.236068");
        assertThat(actual.getAngular()).isEqualByComparingTo("1.107149");
    }

    @Test
    void equalsByComparing_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigGaussian.ZERO.equalsByComparing(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void equalsByComparing_should_return_false_when_real_parts_are_unequal_by_comparing() {
        assertThat(BigGaussian.ZERO.equalsByComparing(BigGaussian.ONE)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_false_when_imaginary_parts_are_unequal_by_comparing() {
        assertThat(BigGaussian.ZERO.equalsByComparing(BigGaussian.I)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_true_when_this_is_this_is_equal_by_comparing_to_other() {
        final var complex = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));
        final var other = new BigGaussian(BigInteger.ONE, BigInteger.valueOf(2L));

        assertThat(complex.equalsByComparing(other)).isTrue();
    }
}

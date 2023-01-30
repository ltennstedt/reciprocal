package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.jupiter.api.Test;

final class BigComplexTests {
    @Test
    void ofReal_should_throw_Exception_when_real_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ofReal(null)).withMessage("real").withNoCause();
    }

    @Test
    void ofReal_should_return_instance() {
        final var actual = BigComplex.ofReal(BigDecimal.ONE);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void ofImaginary_should_throw_Exception_when_imaginary_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ofImaginary(null)).withMessage("imaginary")
            .withNoCause();
    }

    @Test
    void ofImaginary_should_return_instance() {
        final var actual = BigComplex.ofImaginary(BigDecimal.ONE);

        assertThat(actual.getReal()).isZero();
        assertThat(actual.getImaginary()).isOne();
    }

    @Test
    void isInvertible_should_return_false_when_this_is_not_invertible() {
        assertThat(BigComplex.ZERO.isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_this_is_invertible() {
        assertThat(BigComplex.ONE.isInvertible()).isTrue();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.add(null)).withMessage("summand")
            .withNoCause();
    }

    @Test
    void add_should_calculate_sum() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var summand = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.add(summand);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(4L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(6L));
    }

    @Test
    void add_with_MathContext_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.add(null, MathContext.DECIMAL32))
            .withMessage("summand").withNoCause();
    }

    @Test
    void add_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.add(BigComplex.ZERO, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void add_with_MathContext_should_calculate_sum() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var summand = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.add(summand, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(4L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(6L));
    }

    @Test
    void subtract_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_calculate_difference() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var subtrahend = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(-4L));

        final var actual = complex.subtract(subtrahend);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-2L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(6L));
    }

    @Test
    void subtract_with_MathContext_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.subtract(null, MathContext.DECIMAL32))
            .withMessage("subtrahend").withNoCause();
    }

    @Test
    void subtract_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.subtract(BigComplex.ZERO, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void subtract_with_MathContext_should_calculate_difference() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var subtrahend = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(-4L));

        final var actual = complex.subtract(subtrahend, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-2L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(6L));
    }

    @Test
    void multiply_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.multiply(null)).withMessage("factor")
            .withNoCause();
    }

    @Test
    void multiply_should_calculate_product() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var factor = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.multiply(factor);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-5L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    void multiply_with_MathContext_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.multiply(null, MathContext.DECIMAL32))
            .withMessage("factor").withNoCause();
    }

    @Test
    void multiply_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.multiply(BigComplex.ZERO, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void multiply_with_MathContext_should_calculate_product() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var factor = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.multiply(factor, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-5L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.divide(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void divide_should_calculate_quotient() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var divisor = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.divide(divisor);

        assertThat(actual.getReal()).isEqualByComparingTo("0.44");
        assertThat(actual.getImaginary()).isEqualByComparingTo("0.08");
    }

    @Test
    void divide_with_MathContext_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.divide(null, MathContext.DECIMAL32))
            .withMessage("divisor").withNoCause();
    }

    @Test
    void divide_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.divide(BigComplex.ONE, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void divide_with_MathContext_should_calculate_product() {
        final var complex = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L));
        final var divisor = new BigComplex(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));

        final var actual = complex.divide(divisor, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo("0.44");
        assertThat(actual.getImaginary()).isEqualByComparingTo("0.08");
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(-2);

        assertThat(actual.getReal()).isEqualByComparingTo("-0.12");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.16");
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_2() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(2);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-3L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(4));
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_0() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(0);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }

    @Test
    void pow_with_MathContext_should_throw_Exception_when_mathContext_is_null() {
        assertThatNullPointerException().isThrownBy(() -> BigComplex.ZERO.pow(0, null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_minus_2() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(-2, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo("-0.12");
        assertThat(actual.getImaginary()).isEqualByComparingTo("-0.16");
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_2() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(2, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isEqualByComparingTo(BigDecimal.valueOf(-3L));
        assertThat(actual.getImaginary()).isEqualByComparingTo(BigDecimal.valueOf(4));
    }

    @Test
    void pow_with_MathContext_should_calculate_power_when_exponent_is_0() {
        final var actual = new BigComplex(BigDecimal.ONE, BigDecimal.valueOf(2L)).pow(0, MathContext.DECIMAL32);

        assertThat(actual.getReal()).isOne();
        assertThat(actual.getImaginary()).isZero();
    }
}

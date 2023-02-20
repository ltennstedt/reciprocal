package reciprocal.number.fraction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class AbstractFractionTests {
    @Test
    void isNotInvertible_should_return_false_when_this_is_invertible() {
        assertThat(Fraction.ONE.isNotInvertible()).isFalse();
    }

    @Test
    void isNotInvertible_should_return_true_when_this_is_not_invertible() {
        assertThat(Fraction.ZERO.isNotInvertible()).isTrue();
    }

    @Test
    void isNotUnit_should_return_false_when_this_is_a_unit() {
        assertThat(Fraction.ONE.isNotUnit()).isFalse();
    }

    @Test
    void isNotUnit_should_return_true_when_this_is_not_a_unit() {
        assertThat(new Fraction(2L, 3L).isNotUnit()).isTrue();
    }

    @Test
    void isNotDyadic_should_return_false_when_this_is_dyadic() {
        assertThat(new Fraction(1L, 2L).isNotDyadic()).isFalse();
    }

    @Test
    void isNotDyadic_should_return_true_when_this_is_not_dyadic() {
        assertThat(new Fraction(1L, 3L).isNotDyadic()).isTrue();
    }

    @Test
    void isReducible_should_return_false_when_this_is_irreducible() {
        assertThat(new Fraction(2L, 3L).isReducible()).isFalse();
    }

    @Test
    void isReducible_should_return_true_when_this_is_reducible() {
        assertThat(new Fraction(2L, 4L).isReducible()).isTrue();
    }

    @Test
    void isImproper_should_return_false_when_this_is_proper() {
        assertThat(new Fraction(2L, 3L).isImproper()).isFalse();
    }

    @Test
    void isImproper_should_return_true_when_this_is_improper() {
        assertThat(Fraction.ONE.isImproper()).isTrue();
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.divide(null)).withMessage("divisor")
            .withNoCause();
    }

    @Test
    void divide_should_throw_Exception_when_divisor_is_not_invertible() {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.ZERO.divide(Fraction.ZERO))
            .withMessage("this expected to be invertible but this = Fraction{numerator=0, denominator=1}")
            .withNoCause();
    }

    @Test
    void divide_should_calculate_quotient() {
        final var actual = new Fraction(1L, 2L).divide(new Fraction(3L, 4L));

        assertThat(actual.getNumerator()).isEqualByComparingTo(4L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(6L);
    }

    @Test
    void pow_should_throw_Exception_when_exponent_is_negative_and_this_is_not_invertible() {
        assertThatIllegalStateException().isThrownBy(() -> Fraction.ZERO.pow(-1))
            .withMessage("this expected to be invertible but this = Fraction{numerator=0, denominator=1}")
            .withNoCause();
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_negative() {
        final var actual = new Fraction(2L, 3L).pow(-2);

        assertThat(actual.getNumerator()).isEqualByComparingTo(9L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(4L);
    }

    @Test
    void pow_should_calculate_power_when_exponent_is_positive() {
        final var actual = new Fraction(2L, 3L).pow(2);

        assertThat(actual.getNumerator()).isEqualByComparingTo(4L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(9L);
    }
}

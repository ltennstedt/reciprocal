package reciprocal.number.fraction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class AbstractFractionTests {
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

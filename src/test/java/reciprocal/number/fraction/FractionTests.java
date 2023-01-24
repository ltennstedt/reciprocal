package reciprocal.number.fraction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

final class FractionTests {
    @Test
    void constructor_should_throw_an_Exception_when_denominator_is_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Fraction(1L, 0L))
            .withMessage("denominator != 0 expected but denominator = 0").withNoCause();
    }

    @Test
    void ofNumerator_should_return_fraction_with_numerator() {
        final var fraction = Fraction.ofNumerator(2L);

        assertThat(fraction.getNumerator()).isEqualByComparingTo(2L);
        assertThat(fraction.getDenominator()).isOne();
    }

    @Test
    void ofDenominator_should_throw_Exception_when_denominator_is_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.ofDenominator(0L))
            .withMessage("denominator != 0 expected but denominator = 0").withNoCause();
    }

    @Test
    void ofDenominator_should_return_fraction_with_denominator() {
        final var fraction = Fraction.ofDenominator(2L);

        assertThat(fraction.getNumerator()).isOne();
        assertThat(fraction.getDenominator()).isEqualByComparingTo(2L);
    }

    @Test
    void isInvertible_should_return_false_when_numerator_is_0() {
        assertThat(Fraction.ZERO.isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_numerator_is_1() {
        assertThat(Fraction.ONE.isInvertible()).isTrue();
    }

    @Test
    void isUnit_should_return_false_when_numerator_is_2() {
        assertThat(Fraction.ofNumerator(2L).isUnit()).isFalse();
    }

    @Test
    void isUnit_should_return_true_when_numerator_is_1() {
        assertThat(Fraction.ONE.isUnit()).isTrue();
    }

    @Test
    void isDyadic_should_return_false_when_denominator_is_3() {
        assertThat(Fraction.ofDenominator(3L).isDyadic()).isFalse();
    }

    @Test
    void isDyadic_should_return_true_when_denominator_is_4() {
        assertThat(Fraction.ofDenominator(4L).isDyadic()).isTrue();
    }
}

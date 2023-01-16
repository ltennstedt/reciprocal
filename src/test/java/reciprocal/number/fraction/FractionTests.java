package reciprocal.number.fraction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

final class FractionTests {
    @Test
    void constructor_with_only_numerator_should_set_denominator_to_1() {
        final var actual = new Fraction(0L);

        assertThat(actual.getNumerator()).isZero();
        assertThat(actual.getDenominator()).isOne();
    }

    @Test
    void required_arguments_constructor_should_throw_an_Exception_when_denominator_is_0() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Fraction(1L, 0L))
            .withMessage("denominator expected not to be 0 but denominator = 0").withNoCause();
    }

    @Test
    void isInvertible_should_return_false_when_numerator_is_0() {
        assertThat(new Fraction(0L).isInvertible()).isFalse();
    }

    @Test
    void isInvertible_should_return_true_when_numerator_is_1() {
        assertThat(new Fraction(1L).isInvertible()).isTrue();
    }

    @Test
    void isUnit_should_return_false_when_numerator_is_2() {
        assertThat(new Fraction(2L).isUnit()).isFalse();
    }

    @Test
    void isUnit_should_return_true_when_numerator_is_1() {
        assertThat(new Fraction(1L).isUnit()).isTrue();
    }

    @Test
    void isDyadic_should_return_false_when_denominator_is_3() {
        assertThat(new Fraction(1L, 3L).isDyadic()).isFalse();
    }

    @Test
    void isDyadic_should_return_true_when_denominator_is_4() {
        assertThat(new Fraction(1L, 4L).isDyadic()).isTrue();
    }
}

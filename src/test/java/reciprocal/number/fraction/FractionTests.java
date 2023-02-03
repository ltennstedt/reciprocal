package reciprocal.number.fraction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

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

    @Test
    void isIrreducible_should_return_false_when_this_is_reducible() {
        assertThat(new Fraction(-2L, -4L).isIrreducible()).isFalse();
    }

    @Test
    void isIrreducible_should_return_true_when_this_is_irreducible() {
        assertThat(new Fraction(-2L, -3L).isIrreducible()).isTrue();
    }

    @Test
    void isProper_should_return_false_when_this_is_improper() {
        assertThat(new Fraction(-1L, -1L).isProper()).isFalse();
    }

    @Test
    void isProper_should_return_true_when_this_is_proper() {
        assertThat(new Fraction(-1L, -2L).isProper()).isTrue();
    }

    @Test
    void getSignum_should_return_0_when_numerator_is_0() {
        assertThat(Fraction.ZERO.getSignum()).isZero();
    }

    @Test
    void getSignum_should_return_1_when_numerator_and_denominator_are_positive() {
        assertThat(Fraction.ONE.getSignum()).isOne();
    }

    @Test
    void getSignum_should_return_minus_1_when_numerator_is_negative_and_denominator_is_positive() {
        assertThat(Fraction.ofNumerator(-1L).getSignum()).isEqualByComparingTo(-1);
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.add(null)).withMessage("summand").withNoCause();
    }

    @Test
    void add_should_calculate_sum() {
        final var actual = new Fraction(1L, 2L).add(new Fraction(3L, 4L));

        assertThat(actual.getNumerator()).isEqualByComparingTo(10L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(8L);
    }

    @Test
    void subtract_should_throw_Exception_when_subtrahend_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_calculate_difference() {
        final var actual = new Fraction(1L, 2L).subtract(new Fraction(3L, 4L));

        assertThat(actual.getNumerator()).isEqualByComparingTo(-2L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(8L);
    }

    @Test
    void multiply_should_throw_Exception_when_factor_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.multiply(null)).withMessage("factor")
            .withNoCause();
    }

    @Test
    void multiply_should_calculate_product() {
        final var actual = new Fraction(1L, 2L).multiply(new Fraction(3L, 4L));

        assertThat(actual.getNumerator()).isEqualByComparingTo(3L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(8L);
    }

    @Test
    void negate_should_calculate_negated() {
        final var actual = new Fraction(2L, 3L).negate();

        assertThat(actual.getNumerator()).isEqualByComparingTo(-2L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(3L);
    }

    @Test
    void abs_should_calculate_absolute_value() {
        final var actual = new Fraction(-2L, -3L).abs();

        assertThat(actual.getNumerator()).isEqualByComparingTo(2L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(3L);
    }

    @Test
    void expand_should_throw_Exception_when_number_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.expand(null)).withMessage("number")
            .withNoCause();
    }

    @Test
    void expand_should_calculate_expanded() {
        final var actual = new Fraction(2L, 3L).expand(4L);

        assertThat(actual.getNumerator()).isEqualByComparingTo(8L);
        assertThat(actual.getDenominator()).isEqualByComparingTo(12L);
    }

    @Test
    void lessThanOrEqualTo_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException().isThrownBy(() -> Fraction.ZERO.lessThanOrEqualTo(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void lessThanOrEqualTo_should_return_false_when_this_is_greater_than_other() {
        final var fraction = new Fraction(2L, 3L);
        final var other = new Fraction(1L, 2L);

        assertThat(fraction.lessThanOrEqualTo(other)).isFalse();
    }

    @Test
    void lessThanOrEqualTo_should_return_true_when_this_is_equal_to_other() {
        assertThat(Fraction.ZERO.lessThanOrEqualTo(Fraction.ZERO)).isTrue();
    }

    @Test
    void lessThanOrEqualTo_should_return_true_when_this_is_less_than_other() {
        final var fraction = new Fraction(1L, 2L);
        final var other = new Fraction(2L, 3L);

        assertThat(fraction.lessThanOrEqualTo(other)).isTrue();
    }
}

package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class PolarFormTests {
    @Test
    void equalsByComparing_should_throw_Exception_when_other_is_other() {
        final var polarForm = new PolarForm(0.0D, 0.0D);

        assertThatNullPointerException().isThrownBy(() -> polarForm.equalsByComparing(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void equalsByComparing_should_return_false_when_radials_are_unequal_by_comparing() {
        final var polarForm = new PolarForm(0.0D, 0.0D);
        final var other = new PolarForm(1.0D, 0.0D);

        assertThat(polarForm.equalsByComparing(other)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_false_when_angulars_are_unequal_by_comparing() {
        final var polarForm = new PolarForm(0.0D, 0.0D);
        final var other = new PolarForm(0.00D, 1.0D);

        assertThat(polarForm.equalsByComparing(other)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_true_when_this_equals_by_comparing_to_other() {
        final var polarForm = new PolarForm(0.0D, 0.0D);
        final var other = new PolarForm(0.00D, 0.00D);

        assertThat(polarForm.equalsByComparing(other)).isTrue();
    }
}

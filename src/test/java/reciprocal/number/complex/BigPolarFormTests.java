package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

final class BigPolarFormTests {
    @Test
    void equalsByComparing_should_throw_Exception_when_other_is_other() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);

        assertThatNullPointerException().isThrownBy(() -> polarForm.equalsByComparing(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void equalsByComparing_should_return_false_when_radials_are_unequal_by_comparing() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);
        final var other = new BigPolarForm(BigDecimal.ONE, BigDecimal.ZERO);

        assertThat(polarForm.equalsByComparing(other)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_false_when_angulars_are_unequal_by_comparing() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);
        final var other = new BigPolarForm(new BigDecimal("0.0"), BigDecimal.ONE);

        assertThat(polarForm.equalsByComparing(other)).isFalse();
    }

    @Test
    void equalsByComparing_should_return_true_when_this_equals_by_comparing_to_other() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);
        final var other = new BigPolarForm(new BigDecimal("0.0"), new BigDecimal("0.0"));

        assertThat(polarForm.equalsByComparing(other)).isTrue();
    }
}

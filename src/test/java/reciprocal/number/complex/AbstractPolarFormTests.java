package reciprocal.number.complex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

final class AbstractPolarFormTests {
    @Test
    void constructor_should_throw_Exception_when_radial_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigPolarForm(null, null)).withMessage("radial")
            .withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_angular_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigPolarForm(BigDecimal.ZERO, null))
            .withMessage("angular").withNoCause();
    }

    @Test
    void doesNotEqualByComparing_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException()
            .isThrownBy(() -> new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO).doesNotEqualByComparing(null))
            .withMessage("other").withNoCause();
    }

    @Test
    void doesNotEqualByComparing_should_false_when_they_equals_by_comparing() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);
        final var other = new BigPolarForm(BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP), BigDecimal.ZERO);

        assertThat(polarForm.doesNotEqualByComparing(other)).isFalse();
    }

    @Test
    void doesNotEqualByComparing_should_true_when_they_do_not_equal_by_comparing() {
        final var polarForm = new BigPolarForm(BigDecimal.ZERO, BigDecimal.ZERO);
        final var other = new BigPolarForm(BigDecimal.ONE, BigDecimal.ZERO);

        assertThat(polarForm.doesNotEqualByComparing(other)).isTrue();
    }
}

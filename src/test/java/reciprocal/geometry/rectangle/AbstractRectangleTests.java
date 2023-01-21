package reciprocal.geometry.rectangle;

import static java.util.Objects.hash;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

final class AbstractRectangleTests {
    @Test
    void constructor_should_throw_Exception_when_length_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigRectangle(null, null)).withMessage("length")
                .withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_width_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigRectangle(BigDecimal.ONE, null)).withMessage("width")
                .withNoCause();
    }

    @Test
    void hasCode_should_return_hashCode() {
        assertThat(new Rectangle(1.0D, 2.0D).hashCode()).isEqualByComparingTo(hash(1.0D, 2.0D));
    }

    @Test
    void equals_should_return_true_when_this_is_same_as_obj() {
        final var reactangle = new Rectangle(1.0D, 1.0D);

        assertThat(reactangle.equals(reactangle)).isTrue();
    }

    @Test
    void equals_should_return_false_when_obj_is_null() {
        assertThat(new Rectangle(1.0D, 1.0D).equals(null)).isFalse();
    }

    @Test
    void equals_should_return_false_when_classes_are_not_the_same() {
        assertThat(new Rectangle(1.0D, 1.0D).equals(new Object())).isFalse();
    }

    @Test
    void equals_should_return_false_when_lengths_are_not_equal() {
        assertThat(new Rectangle(1.0D, 1.0D).equals(new Rectangle(2.0D, 1.0D))).isFalse();
    }

    @Test
    void equals_should_return_false_when_widths_are_not_equal() {
        assertThat(new Rectangle(1.0D, 1.0D).equals(new Rectangle(1.0D, 2.0D))).isFalse();
    }

    @Test
    void equals_should_return_true_when_all_properties_are_equal() {
        assertThat(new Rectangle(1.0D, 2.0D).equals(new Rectangle(1.0D, 2.0D))).isTrue();
    }

    @Test
    void toString_should_return_toString() {
        assertThat(new Rectangle(1.0D, 2.0D)).hasToString("Rectangle{length=1.0, width=2.0}");
    }
}

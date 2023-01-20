package reciprocal.geometry.rectangle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

final class RectangleTests {
    @Test
    void constructor_should_throw_Exception_when_length_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new Rectangle(0.0D, 1.0D))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("length > 0 expected but length = 0.0")
            .hasNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_width_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new Rectangle(1.0D, 0.0D))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("width > 0 expected but width = 0.0")
            .hasNoCause();
    }

    @Test
    void isSquare_ahould_return_false_when_rectangle_is_not_square() {
        assertThat(new Rectangle(1.0D, 2.0D).isSquare()).isFalse();
    }

    @Test
    void isSquare_ahould_return_true_when_rectangle_is_square() {
        assertThat(new Rectangle(1.0D, 1.0D).isSquare()).isTrue();
    }

    @Test
    void getPerimeter_should_return_perimeter() {
        assertThat(new Rectangle(1.0D, 2.0D).getPerimeter()).isEqualByComparingTo(6.0D);
    }

    @Test
    void getDiagonal_should_return_diagonal() {
        assertThat(new Rectangle(1.0D, 2.0D).getDiagonal()).isEqualByComparingTo(2.23606797749979);
    }

    @Test
    void withLength_should_throw_Exception_when_newLength_is_null() {
        assertThatThrownBy(() -> new Rectangle(1.0D, 1.0D).withLength(null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("newLength")
            .hasNoCause();
    }

    @Test
    void withLength_should_throw_Exception_when_newLength_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new Rectangle(1.0D, 1.0D).withLength(0.0D))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("newLength > 0 expected but newLength = 0.0")
            .hasNoCause();
    }

    @Test
    void withLength_should_return_copy_with_new_length() {
        assertThat(new Rectangle(1.0D, 1.0D).withLength(2.0D))
            .isEqualTo(new Rectangle(2.0D, 1.0D));
    }

    @Test
    void withWidth_should_throw_Exception_when_newWidth_is_null() {
        assertThatThrownBy(() -> new Rectangle(1.0D, 1.0D).withWidth(null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("newWidth")
            .hasNoCause();
    }

    @Test
    void withWidth_should_throw_Exception_when_newWidth_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new Rectangle(1.0D, 1.0D).withWidth(0.0D))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("newWidth > 0 expected but newWidth = 0.0")
            .hasNoCause();
    }

    @Test
    void withWidth_should_return_copy_with_new_width() {
        assertThat(new Rectangle(1.0D, 1.0D).withWidth(2.0D))
            .isEqualTo(new Rectangle(1.0D, 2.0D));
    }
}

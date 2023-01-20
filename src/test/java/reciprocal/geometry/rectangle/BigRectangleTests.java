package reciprocal.geometry.rectangle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.jupiter.api.Test;

final class BigRectangleTests {
    @Test
    void constructor_should_throw_Exception_when_length_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ZERO, BigDecimal.ONE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("length > 0 expected but length = 0")
            .hasNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_width_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ZERO))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("width > 0 expected but width = 0")
            .hasNoCause();
    }

    @Test
    void isSquare_ahould_return_false_when_rectangle_is_not_square() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.TEN).isSquare()).isFalse();
    }

    @Test
    void isSquare_ahould_return_true_when_rectangle_is_square() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).isSquare()).isTrue();
    }

    @Test
    void getPerimeter_should_return_perimeter() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.TEN).getPerimeter())
            .isEqualByComparingTo(BigDecimal.valueOf(22L));
    }

    @Test
    void getDiagonal_should_return_diagonal() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.TEN).getDiagonal())
            .isEqualByComparingTo(new BigDecimal("10.04987562112089027021926491275958"));
    }

    @Test
    void getDiagonal_should_throw_Exception_when_mathContext_is_null() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).getDiagonal(null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("mathContext")
            .hasNoCause();
    }

    @Test
    void getDiagonal_with_MathContext_should_return_diagonal() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.TEN).getDiagonal(MathContext.DECIMAL32))
            .isEqualByComparingTo(new BigDecimal("10.04988"));
    }

    @Test
    void withLength_should_throw_Exception_when_newLength_is_null() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withLength(null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("newLength")
            .hasNoCause();
    }

    @Test
    void withLength_should_throw_Exception_when_newLength_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withLength(BigDecimal.ZERO))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("newLength > 0 expected but newLength = 0")
            .hasNoCause();
    }

    @Test
    void withLength_should_return_copy_with_new_length() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withLength(BigDecimal.TEN))
            .isEqualTo(new BigRectangle(BigDecimal.TEN, BigDecimal.ONE));
    }

    @Test
    void withWidth_should_throw_Exception_when_newWidth_is_null() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withWidth(null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("newWidth")
            .hasNoCause();
    }

    @Test
    void withWidth_should_throw_Exception_when_newWidth_is_less_than_or_equal_to_0() {
        assertThatThrownBy(() -> new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withWidth(BigDecimal.ZERO))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("newWidth > 0 expected but newWidth = 0")
            .hasNoCause();
    }

    @Test
    void withWidth_should_return_copy_with_new_width() {
        assertThat(new BigRectangle(BigDecimal.ONE, BigDecimal.ONE).withWidth(BigDecimal.TEN))
            .isEqualTo(new BigRectangle(BigDecimal.ONE, BigDecimal.TEN));
    }
}

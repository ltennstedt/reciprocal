package reciprocal.linear.matrix;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static reciprocal.linear.matrix.MatrixPreconditions.checkColumnIndex;
import static reciprocal.linear.matrix.MatrixPreconditions.checkRowIndex;

import org.junit.jupiter.api.Test;

final class MatrixPreconditionsTests {
    @Test
    void checkRowIndex_should_throw_Exception_when_rowIndex_is_0() {
        assertThatThrownBy(() -> checkRowIndex(0))
            .isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("rowIndex > 0 expected but rowIndex = 0")
            .hasNoCause();
    }

    @Test
    void checkRowIndex_should_not_throw_Exception_when_rowIndex_is_1() {
        assertThatCode(() -> checkRowIndex(1)).doesNotThrowAnyException();
    }

    @Test
    void checkColumnIndex_should_throw_Exception_when_columnIndex_is_0() {
        assertThatThrownBy(() -> checkColumnIndex(0))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("columnIndex > 0 expected but columnIndex = 0")
            .hasNoCause();
    }

    @Test
    void checkColumnIndex_should_not_throw_Exception_when_columnIndex_is_1() {
        assertThatCode(() -> checkColumnIndex(1)).doesNotThrowAnyException();
    }
}

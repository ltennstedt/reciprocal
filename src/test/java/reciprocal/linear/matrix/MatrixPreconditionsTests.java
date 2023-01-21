package reciprocal.linear.matrix;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static reciprocal.linear.matrix.MatrixPreconditions.checkColumnIndex;
import static reciprocal.linear.matrix.MatrixPreconditions.checkRowIndex;

import org.junit.jupiter.api.Test;

final class MatrixPreconditionsTests {
    @Test
    void checkRowIndex_should_throw_Exception_when_rowIndex_is_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkRowIndex(0))
                .withMessage("rowIndex > 0 expected but rowIndex = 0").withNoCause();
    }

    @Test
    void checkRowIndex_should_not_throw_Exception_when_rowIndex_is_1() {
        assertThatNoException().isThrownBy(() -> checkRowIndex(1));
    }

    @Test
    void checkColumnIndex_should_throw_Exception_when_columnIndex_is_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> checkColumnIndex(0))
                .withMessage("columnIndex > 0 expected but columnIndex = 0").withNoCause();
    }

    @Test
    void checkColumnIndex_should_not_throw_Exception_when_columnIndex_is_1() {
        assertThatNoException().isThrownBy(() -> checkColumnIndex(1));
    }
}

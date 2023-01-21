package reciprocal.linear.matrix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class MatrixEntryTests {
    @Test
    void constructor_should_throw_Exception_when_rowIndex_is_less_than_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new MatrixEntry<>(0, 0, 0))
                .withMessage("rowIndex > 0 expected but rowIndex = 0").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_columnIndex_is_less_than_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new MatrixEntry<>(1, 0, 0))
                .withMessage("columnIndex > 0 expected but columnIndex = 0").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_element_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new MatrixEntry<Integer>(1, 1, null)).withMessage("element")
                .withNoCause();
    }

    @Test
    void withRowIndex_should_throw_Exception_when_newRowIndex_is_less_than_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new MatrixEntry<>(1, 1, 0).withRowIndex(0))
                .withMessage("newRowIndex > 0 expected but newRowIndex = 0").withNoCause();
    }

    @Test
    void withRowIndex_should_return_copy_with_new_row_index() {
        assertThat(new MatrixEntry<>(1, 1, 0).withRowIndex(2)).isEqualTo(new MatrixEntry<>(2, 1, 0));
    }

    @Test
    void withColumnIndex_should_throw_Exception_when_newColumnIndex_is_less_than_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new MatrixEntry<>(1, 1, 0).withColumnIndex(0))
                .withMessage("newColumnIndex > 0 expected but newColumnIndex = 0").withNoCause();
    }

    @Test
    void withColumnIndex_should_return_copy_with_new_column_index() {
        assertThat(new MatrixEntry<>(1, 1, 0).withColumnIndex(2)).isEqualTo(new MatrixEntry<>(1, 2, 0));
    }

    @Test
    void withElement_should_throw_Exception_when_newElement_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new MatrixEntry<>(1, 1, 0).withElement(null))
                .withMessage("newElement").withNoCause();
    }

    @Test
    void withElement_should_return_copy_with_new_element() {
        assertThat(new MatrixEntry<>(1, 1, 0).withElement(1)).isEqualTo(new MatrixEntry<>(1, 1, 1));
    }
}

package reciprocal.linear.matrix;

import static com.google.common.base.Preconditions.checkArgument;

final class MatrixPreconditions {
    private MatrixPreconditions() {
    }

    static void checkRowIndex(final int rowIndex) {
        checkArgument(rowIndex > 0, "rowIndex > 0 expected but rowIndex = %s", rowIndex);
    }

    static void checkColumnIndex(final int columnIndex) {
        checkArgument(columnIndex > 0, "columnIndex > 0 expected but columnIndex = %s", columnIndex);
    }
}

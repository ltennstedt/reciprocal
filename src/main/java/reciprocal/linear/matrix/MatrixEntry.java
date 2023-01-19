package reciprocal.linear.matrix;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

/**
 * Entry for matrices
 *
 * @param <E> element
 * @param rowIndex row index
 * @param columnIndex column index
 * @param element element
 * @since 0.0.1
 */
public record MatrixEntry<E extends Number>(int rowIndex, int columnIndex, @NotNull E element) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param rowIndex row index
     * @param columnIndex column index
     * @param element element
     * @throws IllegalArgumentException when {@code rowIndex <= 0}
     * @throws IllegalArgumentException when {@code columnIndex <= 0}
     * @throws NullPointerException when {@code element == null}
     * @since 0.0.1
     */
    public MatrixEntry {
        checkArgument(rowIndex > 0, "rowIndex > 0 expected but rowIndex = %s", rowIndex);
        checkArgument(columnIndex > 0, "columnIndex > 0 expected but columnIndex = %s", columnIndex);
        requireNonNull(element, "element");
    }

    /**
     * Returns a copy with new row index
     *
     * @param newRowIndex row index
     * @return copy
     * @throws IllegalArgumentException when {@code newIndex < 1}
     * @since 0.0.1
     */
    public @NotNull MatrixEntry<@NotNull E> withRowIndex(final int newRowIndex) {
        checkArgument(newRowIndex > 0, "newRowIndex > 0 expected but newRowIndex = %s", newRowIndex);
        return new MatrixEntry<>(newRowIndex, columnIndex, element);
    }

    /**
     * Returns a copy with new column index
     *
     * @param newColumnIndex column index
     * @return copy
     * @throws IllegalArgumentException when {@code newColumnIndex < 1}
     * @since 0.0.1
     */
    public @NotNull MatrixEntry<@NotNull E> withColumnIndex(final int newColumnIndex) {
        checkArgument(newColumnIndex > 0, "newColumnIndex > 0 expected but newColumnIndex = %s", newColumnIndex);
        return new MatrixEntry<>(rowIndex, newColumnIndex, element);
    }

    /**
     * Returns a copy with new element
     *
     * @param newElement element
     * @return copy
     * @throws NullPointerException when {@code newElement == null}
     * @since 0.0.1
     */
    public @NotNull MatrixEntry<@NotNull E> withElement(final @NotNull E newElement) {
        requireNonNull(newElement, "newElement");
        return new MatrixEntry<>(rowIndex, columnIndex, newElement);
    }
}

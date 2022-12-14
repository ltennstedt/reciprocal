package reciprocal.linear.matrix;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

/**
 * Entry for matrices
 *
 * @param <E> type of element
 * @param rowIndex row index
 * @param columnIndex column index
 * @param element element
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
     */
    public MatrixEntry {
        checkArgument(rowIndex > 0, "expected rowIndex > 0 but rowIndex = %s", rowIndex);
        checkArgument(columnIndex > 0, "expected columnIndex > 0 but columnIndex = %s", columnIndex);
        requireNonNull(element, "element");
    }
}

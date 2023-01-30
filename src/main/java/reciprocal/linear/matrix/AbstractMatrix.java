package reciprocal.linear.matrix;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.noNullElements;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reciprocal.linear.vector.AbstractVector;

/**
 * Base class for matrices
 *
 * @param <E> element
 * @param <V> vector
 * @param <M> matrix
 * @param <N> norm
 * @since 0.0.1
 */
public abstract class AbstractMatrix<E extends Number, V extends AbstractVector<E, V, N>, M extends AbstractMatrix<E, V, M, N>, N extends Number> {
    private final @NotNull List<@NotNull MatrixEntry<@NotNull E>> entries;

    /**
     * Constructor
     *
     * @param rowSize row size
     * @param columnSize column size
     * @param entries entries
     * @throws IllegalArgumentException when {@code rowSize < 1}
     * @throws IllegalArgumentException when {@code columnSize < 1}
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one entry in entries is null
     * @throws IllegalArgumentException when {@code rowIndex < 1 || size < rowIndex} for one row index
     * @throws IllegalArgumentException when {@code columnIndex < 1 || size < columnIndex} for one column index
     * @since 0.0.1
     */
    protected AbstractMatrix(final int rowSize, final int columnSize,
        final @NotNull List<@NotNull MatrixEntry<@NotNull E>> entries) {
        checkArgument(rowSize > 0, "expected rowSize > 0 but rowSize = %s", rowSize);
        checkArgument(columnSize > 0, "expected columnSize > 0 but columnSize = %s", columnSize);
        requireNonNull(entries, "entries");
        noNullElements(entries, "all entries expected not to be null but entries = %s", entries);
        final var rowIndices = entries.stream().map(MatrixEntry::rowIndex).sorted().toList();
        final var expectedRowIndices = Stream.iterate(1, i -> i + 1).limit(rowSize).toList();
        checkArgument(rowIndices.equals(expectedRowIndices), "rowIndices == (1..%s) expected but rowIndices = %s",
            rowSize, rowIndices);
        final var columnIndices = entries.stream().map(MatrixEntry::columnIndex).sorted().toList();
        final var expectedColumnIndices = Stream.iterate(1, i -> i + 1).limit(columnSize).toList();
        checkArgument(rowIndices.equals(expectedColumnIndices),
            "columnIndices == (1..%s) expected but columnIndices = %s", columnSize, columnIndices);
        this.entries = entries.stream().sorted(Comparator.comparingInt(MatrixEntry::rowIndex))
            .sorted(Comparator.comparingInt(MatrixEntry::columnIndex)).toList();
    }

    /**
     * Indicates if {@code this} is square
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isSquare() {
        return getRowSize() == getColumnSize();
    }

    /**
     * Indicates if {@code this} is triangular
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isTriangular() {
        return isUpperTriangular() || isLowerTriangular();
    }

    /**
     * Indicates if {@code this} is upper triangular
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isUpperTriangular();

    /**
     * Indicates if {@code this} is lower triangular
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isLowerTriangular();

    /**
     * Indicates if {@code this} is diagonal
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isDiagonal() {
        return isUpperTriangular() && isLowerTriangular();
    }

    /**
     * Indicates if {@code this} is identity
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isIdentity();

    /**
     * Indicates if {@code this} is invertible
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isInvertible();

    /**
     * Indicates if {@code this} is symmetric
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isSymmetric() {
        return isSquare() && equalsByComparing(transpose());
    }

    /**
     * Indicates if {@code this} is skew symmetric
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isSkewSymmetric() {
        return isSquare() && equalsByComparing(transpose());
    }

    /**
     * Row indices
     *
     * @return row indices
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull Integer> getRowIndices() {
        return entries.stream().map(MatrixEntry::rowIndex).distinct().toList();
    }

    /**
     * Column indices
     *
     * @return column indices
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull Integer> getColumnIndices() {
        return entries.stream().map(MatrixEntry::columnIndex).distinct().toList();
    }

    /**
     * Elements
     *
     * @return elements
     * @since 0.0.1
     */
    public final @NotNull Set<@NotNull E> getElements() {
        return entries.stream().map(MatrixEntry::element).collect(Collectors.toSet());
    }

    /**
     * Diagonal elements
     *
     * @return diagonal elements
     * @since 0.0.1
     */
    public final @NotNull Set<@NotNull E> getDiagonalElements() {
        return entries.stream().filter(e -> e.rowIndex() == e.columnIndex()).map(MatrixEntry::element)
            .collect(Collectors.toSet());
    }

    /**
     * Row size
     *
     * @return row size
     * @since 0.0.1
     */
    public final int getRowSize() {
        return getRowIndices().size();
    }

    /**
     * Column size
     *
     * @return column size
     * @since 0.0.1
     */
    public final int getColumnSize() {
        return getColumnIndices().size();
    }

    /**
     * Calculates the negated
     *
     * @return negated
     * @since 0.0.1
     */
    public abstract @NotNull M negate();

    /**
     * Calculates the transpose
     *
     * @return transpose
     * @since 0.0.1
     */
    public abstract @NotNull M transpose();

    /**
     * Returns if {@code this} is equal to other by comparing
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public abstract boolean equalsByComparing(@NotNull M other);

    /**
     * Entries
     *
     * @return entries
     */
    public final @NotNull List<@NotNull MatrixEntry<@NotNull E>> getEntries() {
        return List.copyOf(entries);
    }

    @Override
    public final int hashCode() {
        return hash(entries);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return entries.equals(((AbstractMatrix<?, ?, ?, ?>) obj).getEntries());
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{entries=" + entries + "}";
    }
}

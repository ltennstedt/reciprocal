package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.noNullElements;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for vectors
 *
 * @param <E> element
 * @param <V> vector
 * @param <N> norm
 * @since 0.0.1
 */
public abstract class AbstractVector<E extends Number, V extends AbstractVector<E, V, N>,
    N extends Number> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Entries
     *
     * @since 0.0.1
     */
    private final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries;

    /**
     * Constructor
     *
     * @param entries entries
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one element in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    protected AbstractVector(final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries) {
        noNullElements(entries, "expected all elements in entries not to be null but entries = %s", entries);
        final List<Integer> expectedIndices = Stream.iterate(1, i -> i + 1).limit(getSize()).toList();
        checkArgument(getIndices().equals(expectedIndices), "expected indices == expectedIndices but %s != %s",
            getIndices(), expectedIndices);
        this.entries = List.copyOf(entries.stream().sorted().toList());
    }

    /**
     * Indices
     *
     * @return indices
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull Integer> getIndices() {
        return entries.stream().map(VectorEntry::index).toList();
    }

    /**
     * Elements
     *
     * @return elements
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull E> getElements() {
        return entries.stream().map(VectorEntry::element).toList();
    }

    /**
     * Size
     *
     * @return size
     * @since 0.0.1
     */
    public final int getSize() {
        return entries.size();
    }

    /**
     * Entries
     *
     * @return entries
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull VectorEntry<@NotNull E>> getEntries() {
        return entries;
    }

    /**
     * Returns the sum of this and the summand
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NotNull V add(@NotNull V summand);

    /**
     * Returns the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NotNull V subtract(@NotNull V subtrahend);

    /**
     * Returns the dot product of this and other
     *
     * @param other other
     * @return dot product
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NotNull E dotProduct(@NotNull V other);

    /**
     * Returns the scalar product pof this and the scalar
     *
     * @param scalar scalar
     * @return scalar product
     * @throws NullPointerException when {@code scalar == null}
     * @since 0.0.1
     */
    public abstract @NotNull V scalarMultiply(@NotNull E scalar);

    /**
     * Returns the negated vector of this
     *
     * @return negated vector
     * @since 0.0.1
     */
    public abstract @NotNull V negate();

    /**
     * Returns if this is orthogonal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract boolean orthogonalTo(@NotNull V other);

    /**
     * Returns the taxicab norm
     *
     * @return taxicab norm
     * @since 0.0.1
     */
    public abstract @NotNull N taxicabNorm();

    /**
     * Returns the square of the euclidean norm
     *
     * @return square of the euclidean norm
     * @since 0.0.1
     */
    public abstract @NotNull N euclideanNormPow2();

    /**
     * Returns the euclidean norm
     *
     * @return euclidean norm
     * @since 0.0.1
     */
    public abstract @NotNull N euclideanNorm();

    /**
     * Returns the maximum norm
     *
     * @return max norm
     * @since 0.0.1
     */
    public abstract @NotNull N maxNorm();

    /**
     * Returns the taxicab distance to other
     *
     * @param other other
     * @return taxicab distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public final @NotNull N taxicabDistance(final @NotNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).taxicabNorm();
    }

    /**
     * Returns the euclidean distance to other
     *
     * @param other other
     * @return euclidean distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are not equal
     * @since 0.0.1
     */
    public final @NotNull N euclideanDistance(final @NotNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).euclideanNorm();
    }

    /**
     * Returns the maximum distance to [other]
     *
     * @param other other
     * @return max distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are not equal
     * @since 0.0.1
     */
    public final @NotNull N maxDistance(final @NotNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).maxNorm();
    }

    /**
     * Returns the element on the index
     *
     * @param index index
     * @return element
     * @throws IllegalArgumentException when {@code index < 1 || index > size}
     * @since 0.0.1
     */
    public final @NotNull E get(final int index) {
        checkArgument(index > 0 && index <= getSize(), "expected 0 < index <= %s but index = %s", getSize(), index);
        return entries.get(index).element();
    }

    /**
     * Returns if this contains element
     *
     * @param element element
     * @return {@link Boolean}
     * @throws NullPointerException when {@code element == null}
     * @since 0.0.1
     */
    public final boolean contains(final @NotNull E element) {
        requireNonNull(element, "element");
        return entries.stream().map(VectorEntry::element).anyMatch(e -> e.equals(element));
    }

    @Override
    public final int hashCode() {
        return Objects.hash(entries);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (AbstractVector<?, ?, ?>) obj;
        return entries.equals(other.getEntries());
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{entries=" + entries + "}";
    }
}

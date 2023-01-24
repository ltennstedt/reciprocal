package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.noNullElements;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.Builder;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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
    private final @NonNull List<@NonNull VectorEntry<@NonNull E>> entries;

    /**
     * Constructor
     *
     * @param size size
     * @param entries entries
     * @throws IllegalArgumentException when {@code size < 1}
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one entry in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    protected AbstractVector(final int size, final @NonNull List<@NonNull VectorEntry<@NonNull E>> entries) {
        checkArgument(size > 0, "size > 0 expected but size = %s", size);
        requireNonNull(entries, "entries");
        noNullElements(entries, "all entries expected not to be null but entries = %s", entries);
        final var indices = entries.stream().map(VectorEntry::index).sorted().toList();
        final var expectedIndices = Stream.iterate(1, i -> i + 1).limit(size).toList();
        checkArgument(indices.equals(expectedIndices), "indices == (1..%s) expected but indices = %s", size, indices);
        this.entries = entries.stream().sorted(Comparator.comparingInt(VectorEntry::index)).toList();
    }

    /**
     * Indices
     *
     * @return indices
     * @since 0.0.1
     */
    public final @NonNull List<@NonNull Integer> getIndices() {
        return entries.stream().map(VectorEntry::index).toList();
    }

    /**
     * Elements
     *
     * @return elements
     * @since 0.0.1
     */
    public final @NonNull List<@NonNull E> getElements() {
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
    public final @NonNull List<@NonNull VectorEntry<@NonNull E>> getEntries() {
        return List.copyOf(entries);
    }

    /**
     * Calculates the sum of this and the summand
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NonNull V add(@NonNull V summand);

    /**
     * Calculates the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NonNull V subtract(@NonNull V subtrahend);

    /**
     * Calculates the dot product of this and other
     *
     * @param other other
     * @return dot product
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NonNull E dotProduct(@NonNull V other);

    /**
     * Calculates the scalar product pof this and the scalar
     *
     * @param scalar scalar
     * @return scalar product
     * @throws NullPointerException when {@code scalar == null}
     * @since 0.0.1
     */
    public abstract @NonNull V scalarMultiply(@NonNull E scalar);

    /**
     * Calculates the negated vector of this
     *
     * @return negated vector
     * @since 0.0.1
     */
    public abstract @NonNull V negate();

    /**
     * Calculates if this is orthogonal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract boolean orthogonalTo(@NonNull V other);

    /**
     * Calculates the taxicab norm
     *
     * @return taxicab norm
     * @since 0.0.1
     */
    public abstract @NonNull N taxicabNorm();

    /**
     * Calculates the euclidean norm
     *
     * @return euclidean norm
     * @since 0.0.1
     */
    public abstract @NonNull N euclideanNorm();

    /**
     * Calculates the maximum norm
     *
     * @return max norm
     * @since 0.0.1
     */
    public abstract @NonNull N maxNorm();

    /**
     * Calculates the taxicab distance to other
     *
     * @param other other
     * @return taxicab distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public final @NonNull N taxicabDistance(final @NonNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).taxicabNorm();
    }

    /**
     * Calculates the euclidean distance to other
     *
     * @param other other
     * @return euclidean distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are not equal
     * @since 0.0.1
     */
    public final @NonNull N euclideanDistance(final @NonNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).euclideanNorm();
    }

    /**
     * Calculates the maximum distance to other
     *
     * @param other other
     * @return max distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are not equal
     * @since 0.0.1
     */
    public final @NonNull N maxDistance(final @NonNull V other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return subtract(other).maxNorm();
    }

    /**
     * Calculates the element on the index
     *
     * @param index index
     * @return element
     * @throws IllegalArgumentException when {@code index < 1 || index > size}
     * @since 0.0.1
     */
    public final @NonNull E getElement(final int index) {
        checkArgument(index > 0 && index <= getSize(), "0 < index <= %s expected but index = %s", getSize(), index);
        return getEntry(index).element();
    }

    /**
     * Calculates the {@link VectorEntry} on the index
     *
     * @param index index
     * @return {@link VectorEntry}
     * @throws IllegalArgumentException when {@code index < 1 || index > size}
     * @since 0.0.1
     */
    public final @NonNull VectorEntry<@NonNull E> getEntry(final int index) {
        checkArgument(index > 0 && index <= getSize(), "0 < index <= %s expected but index = %s", getSize(), index);
        return entries.get(index - 1);
    }

    /**
     * Returns if this contains element
     *
     * @param element element
     * @return {@link Boolean}
     * @throws NullPointerException when {@code element == null}
     * @since 0.0.1
     */
    public final boolean contains(final @NonNull E element) {
        requireNonNull(element, "element");
        return entries.stream().map(VectorEntry::element).anyMatch(e -> e.equals(element));
    }

    /**
     * Calculates the square of the euclidean norm
     *
     * @return square of the euclidean norm
     * @since 0.0.1
     */
    protected abstract @NonNull N euclideanNormPow2();

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
        return entries.equals(((AbstractVector<?, ?, ?>) obj).getEntries());
    }

    @Override
    public final @NonNull String toString() {
        return getClass().getSimpleName() + "{entries=" + entries + "}";
    }

    /**
     * Builder for vectors
     *
     * @param <E> element
     * @param <V> vector
     * @param <B> builder
     * @since 0.0.1
     */
    public abstract static class AbstractVectorBuilder
        <E extends Number, V extends AbstractVector<E, V, ?>, B extends AbstractVectorBuilder<E, V, B>> implements
        Builder<V> {
        /**
         * Size
         *
         * @since 0.0.1
         */
        private final int size;

        /**
         * Entries
         *
         * @since 0.0.1
         */
        private final @NonNull List<@NonNull VectorEntry<@NonNull E>> entries;

        private @NonNull IntFunction<@NonNull E> computationOfAbsentees;

        /**
         * Constructor
         *
         * @param size size
         * @param computationOfAbsentees computation of absentees
         * @throws IllegalArgumentException when {@code size < 1}
         * @throws NullPointerException when {@code computationOfAbsentees == null}
         * @since 0.0.1
         */
        protected AbstractVectorBuilder(final int size, final @NonNull IntFunction<@NonNull E> computationOfAbsentees) {
            checkArgument(size > 0, "size > 0 expected but size = %s", size);
            this.size = size;
            entries = new ArrayList<>(size);
            this.computationOfAbsentees = requireNonNull(computationOfAbsentees, "computationOfAbsentees");
        }

        /**
         * Sets element
         *
         * @param index index
         * @param element element
         * @return {@code this}
         * @throws IllegalArgumentException when {@code index < 1 || index > size}
         * @throws IllegalArgumentException when index already exists
         * @throws NullPointerException when {@code element == null}
         * @since 0.0.1
         */
        public final @NonNull B set(final int index, final @NonNull E element) {
            checkArgument(index > 0 && index <= size, "0 < index <= size expected but index = %s", index);
            checkArgument(entries.stream().map(VectorEntry::index).noneMatch(i -> i == index), "index already exists");
            requireNonNull(element, "element");
            entries.add(new VectorEntry<>(index, element));
            return (B) this;
        }

        /**
         * Sets computation of absentees
         *
         * @param newComputationOfAbsentees computation of absentees
         * @return {@code this}
         * @since 0.0.1
         */
        public final @NonNull B computationOfAbsentees(
            final @NonNull IntFunction<@NonNull E> newComputationOfAbsentees) {
            computationOfAbsentees = requireNonNull(newComputationOfAbsentees, "newComputationOfAbsentees");
            return (B) this;
        }

        /**
         * Size
         *
         * @return size
         * @since 0.0.1
         */
        protected final int getSize() {
            return size;
        }

        /**
         * Entries
         *
         * @return entries
         * @since 0.0.1
         */
        protected final @NonNull List<@NonNull VectorEntry<@NonNull E>> getEntries() {
            return Collections.unmodifiableList(entries);
        }

        /**
         * Computation for absent
         *
         * @return computation for absent
         * @since 0.0.1
         */
        protected final @NonNull IntFunction<@NonNull E> getComputationOfAbsentees() {
            return computationOfAbsentees;
        }

        /**
         * Computes entries
         *
         * @return entries
         * @since 0.0.1
         */
        protected @NonNull List<@NonNull VectorEntry<@NonNull E>> computeEntries() {
            return IntStream.iterate(1, i -> i + 1).boxed().limit(getSize())
                .map(i ->
                    getEntries().stream().filter(e -> e.index() == i).findAny()
                        .orElse(new VectorEntry<>(i, getComputationOfAbsentees().apply(i)))
                ).toList();
        }

        @Override
        public final @NonNull String toString() {
            return getClass().getSimpleName() + "{size=" + size + ", entries=" + entries + "}";
        }
    }
}

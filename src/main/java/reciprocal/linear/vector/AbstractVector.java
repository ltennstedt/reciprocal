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
     * @param size size
     * @param entries entries
     * @throws IllegalArgumentException when {@code size < 1}
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one entry in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    protected AbstractVector(final int size, final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries) {
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
    public final @NotNull Stream<@NotNull Integer> getIndices() {
        return entries.stream().map(VectorEntry::index);
    }

    /**
     * Elements
     *
     * @return elements
     * @since 0.0.1
     */
    public final @NotNull Stream<@NotNull E> getElements() {
        return entries.stream().map(VectorEntry::element);
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
    public final @NotNull Stream<@NotNull VectorEntry<@NotNull E>> getEntries() {
        return entries.stream();
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
    public abstract @NotNull V add(@NotNull V summand);

    /**
     * Calculates the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NotNull V subtract(@NotNull V subtrahend);

    /**
     * Calculates the dot product of this and other
     *
     * @param other other
     * @return dot product
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract @NotNull E dotProduct(@NotNull V other);

    /**
     * Calculates the scalar product pof this and the scalar
     *
     * @param scalar scalar
     * @return scalar product
     * @throws NullPointerException when {@code scalar == null}
     * @since 0.0.1
     */
    public abstract @NotNull V scalarMultiply(@NotNull E scalar);

    /**
     * Calculates the negated vector of this
     *
     * @return negated vector
     * @since 0.0.1
     */
    public abstract @NotNull V negate();

    /**
     * Calculates if this is orthogonal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are not equal
     * @since 0.0.1
     */
    public abstract boolean orthogonalTo(@NotNull V other);

    /**
     * Calculates the taxicab norm
     *
     * @return taxicab norm
     * @since 0.0.1
     */
    public abstract @NotNull N taxicabNorm();

    /**
     * Calculates the euclidean norm
     *
     * @return euclidean norm
     * @since 0.0.1
     */
    public abstract @NotNull N euclideanNorm();

    /**
     * Calculates the maximum norm
     *
     * @return max norm
     * @since 0.0.1
     */
    public abstract @NotNull N maxNorm();

    /**
     * Calculates the taxicab distance to other
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
     * Calculates the euclidean distance to other
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
     * Calculates the maximum distance to other
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
     * Calculates the element on the index
     *
     * @param index index
     * @return element
     * @throws IllegalArgumentException when {@code index < 1 || index > size}
     * @since 0.0.1
     */
    public final @NotNull E getElement(final int index) {
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
    public final @NotNull VectorEntry<@NotNull E> getEntry(final int index) {
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
    public final boolean contains(final @NotNull E element) {
        requireNonNull(element, "element");
        return entries.stream().map(VectorEntry::element).anyMatch(e -> e.equals(element));
    }

    /**
     * Calculates the square of the euclidean norm
     *
     * @return square of the euclidean norm
     * @since 0.0.1
     */
    protected abstract @NotNull N euclideanNormPow2();

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
        return entries.equals(((AbstractVector<?, ?, ?>) obj).getEntries().toList());
    }

    @Override
    public final @NotNull String toString() {
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
        private final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries;

        private @NotNull IntFunction<@NotNull E> computationOfAbsentees;

        /**
         * Constructor
         *
         * @param size size
         * @param computationOfAbsentees computation of absentees
         * @throws IllegalArgumentException when {@code size < 1}
         * @throws NullPointerException when {@code computationOfAbsentees == null}
         * @since 0.0.1
         */
        protected AbstractVectorBuilder(final int size, final @NotNull IntFunction<@NotNull E> computationOfAbsentees) {
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
        @SuppressWarnings("unchecked")
        public final @NotNull B set(final int index, final @NotNull E element) {
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
        @SuppressWarnings("unchecked")
        public final @NotNull B computationOfAbsentees(
            final @NotNull IntFunction<@NotNull E> newComputationOfAbsentees) {
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
        protected final @NotNull List<@NotNull VectorEntry<@NotNull E>> getEntries() {
            return Collections.unmodifiableList(entries);
        }

        /**
         * Computation for absent
         *
         * @return computation for absent
         * @since 0.0.1
         */
        protected final @NotNull IntFunction<@NotNull E> getComputationOfAbsentees() {
            return computationOfAbsentees;
        }

        /**
         * Computes entries
         *
         * @return entries
         * @since 0.0.1
         */
        protected @NotNull List<@NotNull VectorEntry<@NotNull E>> computeEntries() {
            return IntStream.iterate(1, i -> i + 1).boxed().limit(getSize())
                .map(i ->
                    getEntries().stream().filter(e -> e.index() == i).findAny()
                        .orElse(new VectorEntry<>(i, getComputationOfAbsentees().apply(i)))
                ).toList();
        }

        @Override
        public final @NotNull String toString() {
            return getClass().getSimpleName() + "{size=" + size + ", entries=" + entries + "}";
        }
    }
}

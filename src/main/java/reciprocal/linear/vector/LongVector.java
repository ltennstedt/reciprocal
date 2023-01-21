package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.util.List;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a vector which uses {@link Long} as type for its elements
 *
 * @since 0.0.1
 */
public final class LongVector extends AbstractVector<@NotNull Long, @NotNull LongVector, @NotNull Double> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param entries entries
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one element in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    public LongVector(final @NotNull List<@NotNull VectorEntry<@NotNull Long>> entries) {
        super(entries);
    }

    /**
     * Returns a {@link LongVectorBuilder}
     *
     * @param size size
     * @return {@link LongVectorBuilder}
     * @throws IllegalArgumentException when {@code size < 1}
     * @since 0.0.1
     */
    public static LongVectorBuilder ofSize(final int size) {
        checkArgument(size > 0, "size > 0 expected but size = %s", size);
        return new LongVectorBuilder(size);
    }

    @Override
    public @NotNull LongVector add(final @NotNull LongVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(
            getSize() == summand.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            summand.getSize()
        );
        return new LongVector(
            getEntries().stream().map(e -> e.withElement(e.element() + summand.get(e.index()))).toList()
        );
    }

    @Override
    public @NotNull LongVector subtract(final @NotNull LongVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(
            getSize() == subtrahend.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            subtrahend.getSize()
        );
        return new LongVector(
            getEntries().stream().map(e -> e.withElement(e.element() - subtrahend.get(e.index()))).toList()
        );
    }

    @Override
    public @NotNull Long dotProduct(final @NotNull LongVector other) {
        requireNonNull(other, "other");
        checkArgument(
            getSize() == other.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            other.getSize()
        );
        return getEntries().stream().map(e -> e.element() * other.get(e.index())).reduce(Long::sum).orElseThrow();
    }

    @Override
    public @NotNull LongVector scalarMultiply(final @NotNull Long scalar) {
        requireNonNull(scalar, "scalar");
        return new LongVector(getEntries().stream().map(e -> e.withElement(scalar * e.element())).toList());
    }

    @Override
    public @NotNull LongVector negate() {
        return new LongVector(getEntries().stream().map(e -> e.withElement(-e.element())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NotNull LongVector other) {
        requireNonNull(other, "other");
        checkArgument(
            getSize() == other.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            other.getSize()
        );
        return dotProduct(other) == 0L;
    }

    @Override
    public @NotNull Double taxicabNorm() {
        return getElements().stream().map(Math::abs).reduce(Long::sum).map(Long::doubleValue).orElseThrow();
    }

    @Override
    public @NotNull Double euclideanNormPow2() {
        return getElements().stream().map(e -> e * e).reduce(Long::sum).map(Long::doubleValue).orElseThrow();
    }

    @Override
    public @NotNull Double euclideanNorm() {
        return Math.sqrt(euclideanNormPow2());
    }

    @Override
    public @NotNull Double maxNorm() {
        return getElements().stream().map(Math::abs).max(Long::compareTo).map(Long::doubleValue).orElseThrow();
    }

    /**
     * Builder for {@link LongVector LongVectors}
     *
     * @since 0.0.1
     */
    public static final class LongVectorBuilder extends AbstractVectorBuilder<Long, LongVector, LongVectorBuilder> {
        /**
         * Constructor
         *
         * @param size size
         * @throws IllegalArgumentException when {@code size < 1}
         * @since 0.0.1
         */
        public LongVectorBuilder(final int size) {
            super(size, i -> 0L);
        }

        @Override
        public LongVector build() {
            final var entries = IntStream.iterate(1, i -> i + 1).boxed().limit(getSize())
                .map(i ->
                    getEntries().stream().filter(e -> e.index() == i).findAny()
                        .orElse(new VectorEntry<>(i, getComputationForAbsent().apply(i)))
                ).toList();
            return new LongVector(entries);
        }
    }
}

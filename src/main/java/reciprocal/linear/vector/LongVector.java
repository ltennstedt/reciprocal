package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a vector which uses {@link Long} as type for its elements
 *
 * @since 0.0.1
 */
public final class LongVector extends AbstractVector<@NonNull Long, @NonNull LongVector, @NonNull Double> {
    @Serial
    private static final long serialVersionUID = 1L;

    LongVector(final int size, final @NonNull List<@NonNull VectorEntry<@NonNull Long>> entries) {
        super(size, entries);
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
        return new LongVectorBuilder(size);
    }

    @Override
    public @NonNull LongVector add(final @NonNull LongVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new LongVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element() + summand.getElement(e.index()))).toList());
    }

    @Override
    public @NonNull LongVector subtract(final @NonNull LongVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(
            getSize() == subtrahend.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            subtrahend.getSize()
        );
        return new LongVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element() - subtrahend.getElement(e.index()))).toList());
    }

    @Override
    public @NonNull Long dotProduct(final @NonNull LongVector other) {
        requireNonNull(other, "other");
        checkArgument(
            getSize() == other.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            other.getSize()
        );
        return getEntries().stream().map(e -> e.element() * other.getElement(e.index())).reduce(Long::sum)
            .orElseThrow();
    }

    @Override
    public @NonNull LongVector scalarMultiply(final @NonNull Long scalar) {
        requireNonNull(scalar, "scalar");
        return new LongVector(getSize(), getEntries().stream().map(e -> e.withElement(scalar * e.element())).toList());
    }

    @Override
    public @NonNull LongVector negate() {
        return new LongVector(getSize(), getEntries().stream().map(e -> e.withElement(-e.element())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NonNull LongVector other) {
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
    public @NonNull Double taxicabNorm() {
        return getElements().stream().map(Math::abs).reduce(Long::sum).map(Long::doubleValue).orElseThrow();
    }

    @Override
    public @NonNull Double euclideanNormPow2() {
        return getElements().stream().map(e -> e * e).reduce(Long::sum).map(Long::doubleValue).orElseThrow();
    }

    @Override
    public @NonNull Double euclideanNorm() {
        return Math.sqrt(euclideanNormPow2());
    }

    @Override
    public @NonNull Double maxNorm() {
        return getElements().stream().map(Math::abs).max(Long::compareTo).map(Long::doubleValue).orElseThrow();
    }

    /**
     * Builder for {@link LongVector LongVectors}
     *
     * @since 0.0.1
     */
    public static final class LongVectorBuilder extends AbstractVectorBuilder<Long, LongVector, LongVectorBuilder> {
        LongVectorBuilder(final int size) {
            super(size, i -> 0L);
        }

        @Override
        public LongVector build() {
            return new LongVector(getSize(), computeEntries());
        }
    }
}

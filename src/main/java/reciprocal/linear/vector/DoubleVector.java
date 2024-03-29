package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a vector which uses {@link Double} as type for its elements
 *
 * @since 0.0.1
 */
public final class DoubleVector extends AbstractVector<@NotNull Double, @NotNull DoubleVector, @NotNull Double> {
    @Serial
    private static final long serialVersionUID = 1L;

    DoubleVector(final int size, final @NotNull List<@NotNull VectorEntry<@NotNull Double>> entries) {
        super(size, entries);
    }

    /**
     * Returns {@link DoubleVectorBuilder}
     *
     * @param size size
     * @return {@link DoubleVectorBuilder}
     * @throws IllegalArgumentException when {@code size < 1}
     * @since 0.0.1
     */
    public static DoubleVectorBuilder ofSize(final int size) {
        return new DoubleVectorBuilder(size);
    }

    @Override
    public @NotNull DoubleVector add(final @NotNull DoubleVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new DoubleVector(getSize(),
            getEntries().map(e -> e.withElement(e.element() + summand.getElement(e.index()))).toList()
        );
    }

    @Override
    public @NotNull DoubleVector subtract(final @NotNull DoubleVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new DoubleVector(getSize(),
            getEntries().map(e -> e.withElement(e.element() - subtrahend.getElement(e.index()))).toList()
        );
    }

    @Override
    public @NotNull Double dotProduct(final @NotNull DoubleVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().map(e -> e.element() * other.getElement(e.index())).reduce(Double::sum)
            .orElseThrow();
    }

    @Override
    public @NotNull DoubleVector scalarMultiply(final @NotNull Double scalar) {
        requireNonNull(scalar, "scalar");
        return new DoubleVector(getSize(),
            getEntries().map(e -> e.withElement(scalar * e.element())).toList());
    }

    @Override
    public @NotNull DoubleVector negate() {
        return new DoubleVector(getSize(), getEntries().map(e -> e.withElement(-e.element())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NotNull DoubleVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other) == 0L;
    }

    @Override
    public @NotNull Double taxicabNorm() {
        return getElements().map(Math::abs).reduce(Double::sum).orElseThrow();
    }

    @Override
    public @NotNull Double euclideanNorm() {
        return Math.sqrt(euclideanNormPow2());
    }

    @Override
    public @NotNull Double maxNorm() {
        return getElements().map(Math::abs).max(Double::compareTo).orElseThrow();
    }

    @Override
    protected @NotNull Double euclideanNormPow2() {
        return getElements().map(e -> e * e).reduce(Double::sum).orElseThrow();
    }

    /**
     * Builder for {@link DoubleVector DoubleVectors}
     *
     * @since 0.0.1
     */
    public static final class DoubleVectorBuilder
        extends AbstractVectorBuilder<Double, DoubleVector, DoubleVector.DoubleVectorBuilder> {
        DoubleVectorBuilder(final int size) {
            super(size, i -> 0.0D);
        }

        @Override
        public @NotNull DoubleVector build() {
            return new DoubleVector(getSize(), computeEntries());
        }
    }
}

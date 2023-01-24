package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a vector which uses {@link Double} as type for its elements
 *
 * @since 0.0.1
 */
public final class DoubleVector extends AbstractVector<@NonNull Double, @NonNull DoubleVector, @NonNull Double> {
    @Serial
    private static final long serialVersionUID = 1L;

    DoubleVector(final int size, final @NonNull List<@NonNull VectorEntry<@NonNull Double>> entries) {
        super(size, entries);
    }

    @Override
    public @NonNull DoubleVector add(final @NonNull DoubleVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new DoubleVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element() + summand.getElement(e.index()))).toList()
        );
    }

    @Override
    public @NonNull DoubleVector subtract(final @NonNull DoubleVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new DoubleVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element() - subtrahend.getElement(e.index()))).toList()
        );
    }

    @Override
    public @NonNull Double dotProduct(final @NonNull DoubleVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().stream().map(e -> e.element() * other.getElement(e.index())).reduce(Double::sum)
            .orElseThrow();
    }

    @Override
    public @NonNull DoubleVector scalarMultiply(final @NonNull Double scalar) {
        requireNonNull(scalar, "scalar");
        return new DoubleVector(getSize(),
            getEntries().stream().map(e -> e.withElement(scalar * e.element())).toList());
    }

    @Override
    public @NonNull DoubleVector negate() {
        return new DoubleVector(getSize(), getEntries().stream().map(e -> e.withElement(-e.element())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NonNull DoubleVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other) == 0L;
    }

    @Override
    public @NonNull Double taxicabNorm() {
        return getElements().stream().map(Math::abs).reduce(Double::sum).orElseThrow();
    }

    @Override
    public @NonNull Double euclideanNorm() {
        return Math.sqrt(euclideanNormPow2());
    }

    @Override
    public @NonNull Double maxNorm() {
        return getElements().stream().map(Math::abs).max(Double::compareTo).orElseThrow();
    }

    @Override
    protected @NonNull Double euclideanNormPow2() {
        return getElements().stream().map(e -> e * e).reduce(Double::sum).orElseThrow();
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
        public DoubleVector build() {
            return new DoubleVector(getSize(), computeEntries());
        }
    }
}

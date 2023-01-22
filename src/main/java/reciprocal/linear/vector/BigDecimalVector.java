package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a vector that uses {@link BigDecimal} as type for its elements
 *
 * @since 0.0.1
 */
public final class BigDecimalVector
    extends AbstractMathContextVector<@NonNull BigDecimal, @NonNull BigDecimalVector, @NonNull BigDecimal> {
    @Serial
    private static final long serialVersionUID = 1L;

    BigDecimalVector(final int size, final @NonNull List<@NonNull VectorEntry<@NonNull BigDecimal>> entries) {
        super(size, entries);
    }

    /**
     * Returns {@link BigDecimalVectorBuilder}
     *
     * @param size size
     * @return {@link BigDecimalVectorBuilder}
     * @throws IllegalArgumentException when {@code size < 1}
     * @since 0.0.1
     */
    public static BigDecimalVectorBuilder ofSize(final int size) {
        return new BigDecimalVectorBuilder(size);
    }

    @Override
    public @NonNull BigDecimalVector add(final @NonNull BigDecimalVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().add(summand.getElement(e.index())))).toList());
    }

    @Override
    public @NonNull BigDecimalVector add(
        final @NonNull BigDecimalVector summand,
        final @NonNull MathContext mathContext
    ) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().add(summand.getElement(e.index()), mathContext)))
                .toList());
    }

    @Override
    public @NonNull BigDecimalVector subtract(final @NonNull BigDecimalVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().subtract(subtrahend.getElement(e.index()))))
                .toList());
    }

    @Override
    public @NonNull BigDecimalVector subtract(
        final @NonNull BigDecimalVector subtrahend,
        final @NonNull MathContext mathContext
    ) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream()
                .map(e -> e.withElement(e.element().subtract(subtrahend.getElement(e.index()), mathContext)))
                .toList());
    }

    @Override
    public @NonNull BigDecimal dotProduct(final @NonNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().stream().map(e -> e.element().multiply(other.getElement(e.index()))).reduce(BigDecimal::add)
            .orElseThrow();
    }

    @Override
    public @NonNull BigDecimal dotProduct(final @NonNull BigDecimalVector other,
                                          final @NonNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().stream().map(e -> e.element().multiply(other.getElement(e.index()), mathContext))
            .reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    @Override
    public @NonNull BigDecimalVector scalarMultiply(final @NonNull BigDecimal scalar) {
        requireNonNull(scalar, "scalar");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element()))).toList());
    }

    @Override
    public @NonNull BigDecimalVector scalarMultiply(final @NonNull BigDecimal scalar,
                                                    final @NonNull MathContext mathContext) {
        requireNonNull(scalar, "scalar");
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element(), mathContext))).toList());
    }

    @Override
    public @NonNull BigDecimalVector negate() {
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().negate())).toList());
    }

    @Override
    public @NonNull BigDecimalVector negate(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().negate(mathContext))).toList());
    }

    @Override
    public boolean orthogonalTo(final @NonNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other).compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public boolean orthogonalTo(final @NonNull BigDecimalVector other, final @NonNull MathContext mathContext) {
        requireNonNull(other, "other");
        requireNonNull(mathContext, "mathContext");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other, mathContext).compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public @NonNull BigDecimal taxicabNorm() {
        return getElements().stream().map(BigDecimal::abs).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    public @NonNull BigDecimal taxicabNorm(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(a -> a.abs(mathContext)).reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    @Override
    public @NonNull BigDecimal euclideanNormPow2() {
        return getElements().stream().map(e -> e.multiply(e)).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    public @NonNull BigDecimal euclideanNormPow2(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(e -> e.multiply(e, mathContext)).reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    @Override
    public @NonNull BigDecimal euclideanNorm() {
        return euclideanNormPow2().sqrt(MathContext.UNLIMITED);
    }

    @Override
    public @NonNull BigDecimal euclideanNorm(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        requireNonNull(mathContext, "mathContext");
        return euclideanNormPow2(mathContext).sqrt(mathContext);
    }

    @Override
    public @NonNull BigDecimal maxNorm() {
        return getElements().stream().map(BigDecimal::abs).max(BigDecimal::compareTo).orElseThrow();
    }

    @Override
    public @NonNull BigDecimal maxNorm(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(a -> a.abs(mathContext)).max(BigDecimal::compareTo).orElseThrow();
    }

    /**
     * Builder for {@link BigDecimalVector BigDecimalVectors}
     *
     * @since 0.0.1
     */
    public static final class BigDecimalVectorBuilder extends
        AbstractVectorBuilder<@NonNull BigDecimal, @NonNull BigDecimalVector, @NonNull BigDecimalVectorBuilder> {
        BigDecimalVectorBuilder(final int size) {
            super(size, i -> BigDecimal.ZERO);
        }

        @Override
        public @NonNull BigDecimalVector build() {
            return new BigDecimalVector(getSize(), computeEntries());
        }
    }
}

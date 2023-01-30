package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a vector that uses {@link BigDecimal} as type for its elements
 *
 * @since 0.0.1
 */
public final class BigDecimalVector
    extends AbstractMathContextVector<@NotNull BigDecimal, @NotNull BigDecimalVector, @NotNull BigDecimal> {
    @Serial
    private static final long serialVersionUID = 1L;

    BigDecimalVector(final int size, final @NotNull List<@NotNull VectorEntry<@NotNull BigDecimal>> entries) {
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
    public @NotNull BigDecimalVector add(final @NotNull BigDecimalVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().add(summand.getElement(e.index())))).toList());
    }

    @Override
    public @NotNull BigDecimalVector add(final @NotNull BigDecimalVector summand,
        final @NotNull MathContext mathContext) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().add(summand.getElement(e.index()), mathContext)))
                .toList());
    }

    @Override
    public @NotNull BigDecimalVector subtract(final @NotNull BigDecimalVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().subtract(subtrahend.getElement(e.index()))))
                .toList());
    }

    @Override
    public @NotNull BigDecimalVector subtract(
        final @NotNull BigDecimalVector subtrahend,
        final @NotNull MathContext mathContext
    ) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream()
                .map(e -> e.withElement(e.element().subtract(subtrahend.getElement(e.index()), mathContext)))
                .toList());
    }

    @Override
    public @NotNull BigDecimal dotProduct(final @NotNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().stream().map(e -> e.element().multiply(other.getElement(e.index()))).reduce(BigDecimal::add)
            .orElseThrow();
    }

    @Override
    public @NotNull BigDecimal dotProduct(final @NotNull BigDecimalVector other,
        final @NotNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        requireNonNull(mathContext, "mathContext");
        return getEntries().stream().map(e -> e.element().multiply(other.getElement(e.index()), mathContext))
            .reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    @Override
    public @NotNull BigDecimalVector scalarMultiply(final @NotNull BigDecimal scalar) {
        requireNonNull(scalar, "scalar");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element()))).toList());
    }

    @Override
    public @NotNull BigDecimalVector scalarMultiply(final @NotNull BigDecimal scalar,
        final @NotNull MathContext mathContext) {
        requireNonNull(scalar, "scalar");
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element(), mathContext))).toList());
    }

    @Override
    public @NotNull BigDecimalVector negate() {
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().negate())).toList());
    }

    @Override
    public @NotNull BigDecimalVector negate(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigDecimalVector(getSize(),
            getEntries().stream().map(e -> e.withElement(e.element().negate(mathContext))).toList());
    }

    @Override
    public boolean orthogonalTo(final @NotNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other).compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public boolean orthogonalTo(final @NotNull BigDecimalVector other, final @NotNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        requireNonNull(mathContext, "mathContext");
        return dotProduct(other, mathContext).compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public @NotNull BigDecimal taxicabNorm() {
        return getElements().stream().map(BigDecimal::abs).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    public @NotNull BigDecimal taxicabNorm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(a -> a.abs(mathContext)).reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    @Override
    public @NotNull BigDecimal euclideanNorm() {
        return euclideanNormPow2().sqrt(MathContext.UNLIMITED);
    }

    @Override
    public @NotNull BigDecimal euclideanNorm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return euclideanNormPow2(mathContext).sqrt(mathContext);
    }

    @Override
    public @NotNull BigDecimal maxNorm() {
        return getElements().stream().map(BigDecimal::abs).max(BigDecimal::compareTo).orElseThrow();
    }

    @Override
    public @NotNull BigDecimal maxNorm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(a -> a.abs(mathContext)).max(BigDecimal::compareTo).orElseThrow();
    }

    @Override
    protected @NotNull BigDecimal euclideanNormPow2() {
        return getElements().stream().map(e -> e.multiply(e)).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    protected @NotNull BigDecimal euclideanNormPow2(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getElements().stream().map(e -> e.multiply(e, mathContext)).reduce((a, b) -> a.add(b, mathContext))
            .orElseThrow();
    }

    /**
     * Builder for {@link BigDecimalVector BigDecimalVectors}
     *
     * @since 0.0.1
     */
    public static final class BigDecimalVectorBuilder extends
        AbstractVectorBuilder<@NotNull BigDecimal, @NotNull BigDecimalVector, @NotNull BigDecimalVectorBuilder> {
        BigDecimalVectorBuilder(final int size) {
            super(size, i -> BigDecimal.ZERO);
        }

        @Override
        public @NotNull BigDecimalVector build() {
            return new BigDecimalVector(getSize(), computeEntries());
        }
    }
}

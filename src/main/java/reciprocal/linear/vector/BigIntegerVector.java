package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a vector which uses {@link BigInteger} as type for its elements
 *
 * @since 0.0.1
 */
public final class BigIntegerVector
    extends AbstractVector<@NotNull BigInteger, @NotNull BigIntegerVector, @NotNull BigDecimal> {
    @Serial
    private static final long serialVersionUID = 1L;

    BigIntegerVector(final int size, final @NotNull List<@NotNull VectorEntry<@NotNull BigInteger>> entries) {
        super(size, entries);
    }

    /**
     * Returns {@link BigIntegerVectorBuilder}
     *
     * @param size size
     * @return {@link BigIntegerVectorBuilder}
     * @throws IllegalArgumentException when {@code size < 1}
     * @since 0.0.1
     */
    public static BigIntegerVectorBuilder ofSize(final int size) {
        return new BigIntegerVectorBuilder(size);
    }

    @Override
    public @NotNull BigIntegerVector add(final @NotNull BigIntegerVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(getSize() == summand.getSize(), "equal sizes expected but %s != %s", getSize(),
            summand.getSize());
        return new BigIntegerVector(getSize(),
            getEntries().map(e -> e.withElement(e.element().add(summand.getElement(e.index())))).toList()
        );
    }

    @Override
    public @NotNull BigIntegerVector subtract(final @NotNull BigIntegerVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(getSize() == subtrahend.getSize(), "equal sizes expected but %s != %s", getSize(),
            subtrahend.getSize());
        return new BigIntegerVector(getSize(),
            getEntries().map(e -> e.withElement(e.element().subtract(subtrahend.getElement(e.index()))))
                .toList());
    }

    @Override
    public @NotNull BigInteger dotProduct(final @NotNull BigIntegerVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return getEntries().map(e -> e.element().multiply(other.getElement(e.index()))).reduce(BigInteger::add)
            .orElseThrow();
    }

    @Override
    public @NotNull BigIntegerVector scalarMultiply(final @NotNull BigInteger scalar) {
        requireNonNull(scalar, "scalar");
        return new BigIntegerVector(getSize(),
            getEntries().map(e -> e.withElement(scalar.multiply(e.element()))).toList());
    }

    @Override
    public @NotNull BigIntegerVector negate() {
        return new BigIntegerVector(getSize(),
            getEntries().map(e -> e.withElement(e.element().negate())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NotNull BigIntegerVector other) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        return dotProduct(other).compareTo(BigInteger.ZERO) == 0;
    }

    @Override
    public @NotNull BigDecimal taxicabNorm() {
        return getElements().map(BigInteger::abs).reduce(BigInteger::add).map(BigDecimal::new).orElseThrow();
    }

    @Override
    public @NotNull BigDecimal euclideanNorm() {
        return euclideanNormPow2().sqrt(MathContext.UNLIMITED);
    }

    /**
     * Calculates the Euclidean norm
     *
     * @param mathContext {@link MathContext}
     * @return euclidean norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigDecimal euclideanNorm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return euclideanNormPow2().sqrt(mathContext);
    }

    @Override
    public @NotNull BigDecimal maxNorm() {
        return getElements().map(BigInteger::abs).max(BigInteger::compareTo).map(BigDecimal::new)
            .orElseThrow();
    }

    @Override
    protected @NotNull BigDecimal euclideanNormPow2() {
        return getElements().map(e -> e.multiply(e)).reduce(BigInteger::add).map(BigDecimal::new)
            .orElseThrow();
    }

    /**
     * Builder for {@link BigIntegerVector BigIntegerVectors}
     *
     * @since 0.0.1
     */
    public static final class BigIntegerVectorBuilder extends
        AbstractVectorBuilder<@NotNull BigInteger, @NotNull BigIntegerVector, @NotNull BigIntegerVectorBuilder> {
        BigIntegerVectorBuilder(final int size) {
            super(size, i -> BigInteger.ZERO);
        }

        @Override
        public BigIntegerVector build() {
            return new BigIntegerVector(getSize(), computeEntries());
        }
    }
}

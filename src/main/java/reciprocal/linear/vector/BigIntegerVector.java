package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a vector which uses {@link BigInteger} as type for its elements
 *
 * @since 0.0.1
 */
public final class BigIntegerVector
        extends AbstractVector<@NonNull BigInteger, @NonNull BigIntegerVector, @NonNull BigDecimal> {
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
    public BigIntegerVector(final @NonNull List<@NonNull VectorEntry<@NonNull BigInteger>> entries) {
        super(entries);
    }

    @Override
    public @NonNull BigIntegerVector add(final @NonNull BigIntegerVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(
                getSize() == summand.getSize(),
                "equal sizes expected but %s != %s",
                getSize(),
                summand.getSize()
        );
        return new BigIntegerVector(
                getEntries().stream().map(e -> e.withElement(e.element().add(summand.get(e.index())))).toList()
        );
    }

    @Override
    public @NonNull BigIntegerVector subtract(final @NonNull BigIntegerVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(
                getSize() == subtrahend.getSize(),
                "equal sizes expected but %s != %s",
                getSize(),
                subtrahend.getSize()
        );
        return new BigIntegerVector(
                getEntries().stream().map(e -> e.withElement(e.element().subtract(subtrahend.get(e.index())))).toList()
        );
    }

    @Override
    public @NonNull BigInteger dotProduct(final @NonNull BigIntegerVector other) {
        requireNonNull(other, "other");
        checkArgument(
                getSize() == other.getSize(),
                "equal sizes expected but %s != %s",
                getSize(),
                other.getSize()
        );
        return getEntries().stream().map(e -> e.element().multiply(other.get(e.index()))).reduce(BigInteger::add)
                .orElseThrow();
    }

    @Override
    public @NonNull BigIntegerVector scalarMultiply(final @NonNull BigInteger scalar) {
        requireNonNull(scalar, "scalar");
        return new BigIntegerVector(
                getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element()))).toList());
    }

    @Override
    public @NonNull BigIntegerVector negate() {
        return new BigIntegerVector(getEntries().stream().map(e -> e.withElement(e.element().negate())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NonNull BigIntegerVector other) {
        requireNonNull(other, "other");
        checkArgument(
                getSize() == other.getSize(),
                "equal sizes expected but %s != %s",
                getSize(),
                other.getSize()
        );
        return dotProduct(other).compareTo(BigInteger.ZERO) == 0;
    }

    @Override
    public @NonNull BigDecimal taxicabNorm() {
        return getElements().stream().map(BigInteger::abs).reduce(BigInteger::add).map(BigDecimal::new).orElseThrow();
    }

    @Override
    public @NonNull BigDecimal euclideanNormPow2() {
        return getElements().stream().map(e -> e.multiply(e)).reduce(BigInteger::add).map(BigDecimal::new)
                .orElseThrow();
    }

    @Override
    public @NonNull BigDecimal euclideanNorm() {
        return euclideanNormPow2().sqrt(MathContext.UNLIMITED);
    }

    /**
     * Returns the euclidean
     *
     * @param mathContext {@link MathContext}
     * @return euclidean norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NonNull BigDecimal euclideanNorm(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return euclideanNormPow2().sqrt(mathContext);
    }

    @Override
    public @NonNull BigDecimal maxNorm() {
        return getElements().stream().map(BigInteger::abs).max(BigInteger::compareTo).map(BigDecimal::new)
                .orElseThrow();
    }

    /**
     * Builder for {@link BigIntegerVector BigIntegerVectors}
     *
     * @since 0.0.1
     */
    public static final class BigIntegerVectorBuilder extends
            AbstractVectorBuilder<@NonNull BigInteger, @NonNull BigIntegerVector, @NonNull BigIntegerVectorBuilder> {
        BigIntegerVectorBuilder(final int size) {
            super(size, i -> BigInteger.ZERO);
        }

        @Override
        public BigIntegerVector build() {
            return new BigIntegerVector(computeEntries());
        }
    }
}

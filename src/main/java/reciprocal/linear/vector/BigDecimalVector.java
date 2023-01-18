package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a vector which uses {@link BigDecimal} as type for its elements
 *
 * @since 0.0.1
 */
public final class BigDecimalVector
    extends AbstractVector<@NotNull BigDecimal, @NotNull BigDecimalVector, @NotNull BigDecimal> {
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
    public BigDecimalVector(final @NotNull List<@NotNull VectorEntry<@NotNull BigDecimal>> entries) {
        super(entries);
    }

    @Override
    public @NotNull BigDecimalVector add(final @NotNull BigDecimalVector summand) {
        requireNonNull(summand, "summand");
        checkArgument(
            getSize() == summand.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            summand.getSize()
        );
        return new BigDecimalVector(
            getEntries().stream().map(e -> e.withElement(e.element().add(summand.get(e.index())))).toList()
        );
    }

    @Override
    public @NotNull BigDecimalVector subtract(final @NotNull BigDecimalVector subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        checkArgument(
            getSize() == subtrahend.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            subtrahend.getSize()
        );
        return new BigDecimalVector(
            getEntries().stream().map(e -> e.withElement(e.element().subtract(subtrahend.get(e.index())))).toList()
        );
    }

    @Override
    public @NotNull BigDecimal dotProduct(final @NotNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(
            getSize() == other.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            other.getSize()
        );
        return getEntries().stream().map(e -> e.element().multiply(other.get(e.index()))).reduce(BigDecimal::add)
            .orElseThrow();
    }

    @Override
    public @NotNull BigDecimalVector scalarMultiply(final @NotNull BigDecimal scalar) {
        requireNonNull(scalar, "scalar");
        return new BigDecimalVector(
            getEntries().stream().map(e -> e.withElement(scalar.multiply(e.element()))).toList());
    }

    @Override
    public @NotNull BigDecimalVector negate() {
        return new BigDecimalVector(getEntries().stream().map(e -> e.withElement(e.element().negate())).toList());
    }

    @Override
    public boolean orthogonalTo(final @NotNull BigDecimalVector other) {
        requireNonNull(other, "other");
        checkArgument(
            getSize() == other.getSize(),
            "equal sizes expected but %s != %s",
            getSize(),
            other.getSize()
        );
        return dotProduct(other).compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public @NotNull BigDecimal taxicabNorm() {
        return getElements().stream().map(BigDecimal::abs).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    public @NotNull BigDecimal euclideanNormPow2() {
        return getElements().stream().map(e -> e.multiply(e)).reduce(BigDecimal::add).orElseThrow();
    }

    @Override
    public @NotNull BigDecimal euclideanNorm() {
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
    public @NotNull BigDecimal euclideanNorm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return euclideanNormPow2().sqrt(mathContext);
    }

    @Override
    public @NotNull BigDecimal maxNorm() {
        return getElements().stream().map(BigDecimal::abs).max(BigDecimal::compareTo).orElseThrow();
    }
}

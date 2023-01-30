package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.MathContext;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for vectors with {@link MathContext}
 *
 * @param <E> element
 * @param <V> vector
 * @param <N> norm
 * @since 0.0.1
 */
public abstract class AbstractMathContextVector<E extends Number, V extends AbstractMathContextVector<E, V, N>, N extends Number>
    extends AbstractVector<E, V, N> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param size size
     * @param vectorEntries entries
     * @throws IllegalArgumentException when {@code size < 1}
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one element in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    protected AbstractMathContextVector(final int size,
        final @NotNull List<@NotNull VectorEntry<@NotNull E>> vectorEntries) {
        super(size, vectorEntries);
    }

    /**
     * Calculates the sum of this and the summand
     *
     * @param summand summand
     * @param mathContext {@link MathContext}
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @throws IllegalArgumentException when sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract V add(@NotNull V summand, @NotNull MathContext mathContext);

    /**
     * Calculates the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @param mathContext {@link MathContext}
     * @return difference
     * @throws NullPointerException when {@code summand == null}
     * @throws IllegalArgumentException when sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract V subtract(@NotNull V subtrahend, @NotNull MathContext mathContext);

    /**
     * Calculates the dot product of this and other
     *
     * @param other other
     * @param mathContext {@link MathContext}
     * @return dot product
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract E dotProduct(@NotNull V other, @NotNull MathContext mathContext);

    /**
     * Calculates the scalar product pof this and the scalar
     *
     * @param scalar scalar
     * @param mathContext {@link MathContext}
     * @return scalar product
     * @throws NullPointerException when {@code scalar == null}
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract @NotNull V scalarMultiply(@NotNull E scalar, @NotNull MathContext mathContext);

    /**
     * Calculates the negated vector of this
     *
     * @param mathContext {@link MathContext}
     * @return negated vector
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract @NotNull V negate(@NotNull MathContext mathContext);

    /**
     * Calculates if this is orthogonal to other
     *
     * @param other other
     * @param mathContext {@link MathContext}
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract boolean orthogonalTo(@NotNull V other, @NotNull MathContext mathContext);

    /**
     * Calculates the taxicab norm
     *
     * @param mathContext {@link MathContext}
     * @return taxicab norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract @NotNull N taxicabNorm(@NotNull MathContext mathContext);

    /**
     * Calculates the euclidean norm
     *
     * @param mathContext {@link MathContext}
     * @return euclidean norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract @NotNull N euclideanNorm(@NotNull MathContext mathContext);

    /**
     * Calculates the maximum norm
     *
     * @param mathContext {@link MathContext}
     * @return max norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public abstract @NotNull N maxNorm(@NotNull MathContext mathContext);

    /**
     * Calculates the taxicab distance to other
     *
     * @param other other
     * @param mathContext {@link MathContext}
     * @return taxicab distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException when sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public final @NotNull N taxicabDistance(final @NotNull V other, final @NotNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        requireNonNull(mathContext, "mathContext");
        return subtract(other, mathContext).taxicabNorm(mathContext);
    }

    /**
     * Calculates the euclidean distance to other
     *
     * @param other other
     * @param mathContext {@link MathContext}
     * @return euclidean distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public final @NotNull N euclideanDistance(final @NotNull V other, final @NotNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        requireNonNull(mathContext, "mathContext");
        return subtract(other, mathContext).euclideanNorm(mathContext);
    }

    /**
     * Calculates the maximum distance to other
     *
     * @param other other
     * @param mathContext {@link MathContext}
     * @return max distance
     * @throws NullPointerException when {@code other == null}
     * @throws IllegalArgumentException if sizes are unequal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public final @NotNull N maxDistance(final @NotNull V other, final @NotNull MathContext mathContext) {
        requireNonNull(other, "other");
        checkArgument(getSize() == other.getSize(), "equal sizes expected but %s != %s", getSize(), other.getSize());
        requireNonNull(mathContext, "mathContext");
        return subtract(other, mathContext).maxNorm(mathContext);
    }

    /**
     * Calculates the square of the euclidean norm
     *
     * @param mathContext {@link MathContext}
     * @return square of the euclidean norm
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    protected abstract @NotNull N euclideanNormPow2(@NotNull MathContext mathContext);
}

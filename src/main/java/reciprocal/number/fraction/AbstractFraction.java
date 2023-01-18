package reciprocal.number.fraction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for fractions
 *
 * @param <N> {@link Number}
 * @param <T> {@link AbstractFraction}
 * @param <Q> quotient
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractFraction<N extends Number, T extends AbstractFraction<N, T, Q>, Q extends Number>
    extends Number implements Comparable<T> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Numerator
     *
     * @since 0.0.1
     */
    private final @NotNull N numerator;

    /**
     * Denominator
     *
     * @since 0.0.1
     */
    private final @NotNull N denominator;

    /**
     * All arguments Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws NullPointerException when {@code numerator == null}
     * @throws NullPointerException when {@code denominator == null}
     * @since 0.0.1
     */
    protected AbstractFraction(final @NotNull N numerator, final @NotNull N denominator) {
        this.numerator = requireNonNull(numerator, "numerator");
        this.denominator = requireNonNull(denominator, "denominator");
    }

    /**
     * Indicates if this is invertible
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isInvertible();

    /**
     * Indicates if this is not invertible
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isNotInvertible() {
        return !isInvertible();
    }

    /**
     * Indicates if this is a unit
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isUnit();

    /**
     * Indicates if this is not a unit
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isNotUnit() {
        return !isUnit();
    }

    /**
     * Indicates if this is dyadic
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isDyadic();

    /**
     * Indicates if this is not dyadic
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isNotDyadic() {
        return !isDyadic();
    }

    /**
     * Indicates if this is irreducible
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isIrreducible();

    /**
     * Indicates if this is not reducible
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isReducible() {
        return !isIrreducible();
    }

    /**
     * Indicates if this is proper
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isProper();

    /**
     * Indicates if this is improper
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isImproper() {
        return !isProper();
    }

    /**
     * Signum
     *
     * @return signum
     * @since 0.0.1
     */
    public abstract int getSignum();

    /**
     * Returns the sum of this and the summand
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @since 0.0.1
     */
    public abstract @NotNull T add(@NotNull T summand);

    /**
     * Returns the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @since 0.0.1
     */
    public abstract @NotNull T subtract(@NotNull T subtrahend);

    /**
     * Returns the product of this and the factor
     *
     * @param factor factor
     * @return product
     * @throws NullPointerException when {@code factor == null}
     * @since 0.0.1
     */
    public abstract @NotNull T multiply(@NotNull T factor);

    /**
     * Returns the quotient of this and the divisor
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     * @since 0.0.1
     */
    public final @NotNull T divide(final @NotNull T divisor) {
        requireNonNull(divisor, "divisor");
        return multiply(divisor.invert());
    }

    /**
     * Returns the power of this raised by the exponent
     *
     * @param exponent exponent
     * @return power
     * @since 0.0.1
     */
    public final @NotNull T pow(final int exponent) {
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        return getOne();
    }

    /**
     * Returns the negated
     *
     * @return negated
     * @since 0.0.1
     */
    public abstract @NotNull T negate();

    /**
     * Returns the inverted
     *
     * @return inverted
     * @throws IllegalStateException if this is not invertible
     * @since 0.0.1
     */
    public final @NotNull T invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return getConstructor().apply(denominator, numerator);
    }

    /**
     * Returns the absolute value
     *
     * @return absolute value
     * @since 0.0.1
     */
    public abstract @NotNull T abs();

    /**
     * Returns the expanded fraction
     *
     * @param number number
     * @return expanded
     * @throws NullPointerException when {@code number == null}
     * @since 0.0.1
     */
    public abstract @NotNull T expand(@NotNull N number);

    /**
     * Returns if this is less than or equal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public abstract boolean lessThanOrEqualTo(@NotNull T other);

    /**
     * Returns if this is greater than or equal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean greaterThanOrEqualTo(final @NotNull T other) {
        requireNonNull(other, "other");
        return !lessThanOrEqualTo(other) || equivalent(other);
    }

    /**
     * Returns if this is strictly less than other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean lessThan(final @NotNull T other) {
        requireNonNull(other, "other");
        return !greaterThanOrEqualTo(other);
    }

    /**
     * Returns if this is strictly greater than other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean greaterThan(final @NotNull T other) {
        requireNonNull(other, "other");
        return !lessThanOrEqualTo(other);
    }

    /**
     * Returns the minimum of this and other
     *
     * @param other other
     * @return minimum
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    public final @NotNull T min(final @NotNull T other) {
        requireNonNull(other, "other");
        return greaterThan(other) ? other : (T) this;
    }

    /**
     * Returns the maximum of this and other
     *
     * @param other other
     * @return minimum
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    public @NotNull T max(final @NotNull T other) {
        requireNonNull(other, "other");
        return lessThan(other) ? other : (T) this;
    }

    /**
     * Returns this incremented by 1
     *
     * @return increment
     * @since 0.0.1
     */
    public final @NotNull T inc() {
        return add(getOne());
    }

    /**
     * Returns this decremented by 1
     *
     * @return decrement
     * @since 0.0.1
     */
    public final @NotNull T dec() {
        return subtract(getOne());
    }

    /**
     * Returns the normalized
     *
     * @return normalized
     * @since 0.0.1
     */
    public abstract @NotNull T normalize();

    /**
     * Returns the reduced
     *
     * @return reduced
     * @since 0.0.1
     */
    public abstract @NotNull T reduce();

    /**
     * Returns if this is equivalent to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code number == null}
     * @since 0.0.1
     */
    public final boolean equivalent(final @NotNull T other) {
        requireNonNull(other, "other");
        return normalize().reduce().equals(other.normalize().reduce());
    }

    /**
     * Returns this as {@link BigDecimal}
     *
     * @return {@link BigDecimal}
     * @since 0.0.1
     */
    public abstract @NotNull BigDecimal toBigDecimal();

    @Override
    public final int intValue() {
        return toBigDecimal().intValue();
    }

    @Override
    public final long longValue() {
        return toBigDecimal().longValue();
    }

    @Override
    public final float floatValue() {
        return toBigDecimal().floatValue();
    }

    @Override
    public final double doubleValue() {
        return toBigDecimal().doubleValue();
    }

    /**
     * 1
     *
     * @return 1
     * @since 0.0.1
     */
    protected abstract @NotNull T getOne();

    /**
     * Constructor
     *
     * @return constructor
     * @since 0.0.1
     */
    protected abstract @NotNull BiFunction<N, N, T> getConstructor();

    /**
     * Binary + operator for Groovy and Kotlin
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @since 0.0.1
     */
    public final @NotNull T plus(final @NotNull T summand) {
        requireNonNull(summand, "summand");
        return add(summand);
    }

    /**
     * Binary - operator for Groovy and Kotlin
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @since 0.0.1
     */
    public final @NotNull T minus(final @NotNull T subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        return subtract(subtrahend);
    }

    /**
     * Binary / operator for Groovy and Kotlin
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     * @since 0.0.1
     */
    public final @NotNull T div(final @NotNull T divisor) {
        requireNonNull(divisor, "divisor");
        return divide(divisor);
    }

    /**
     * Binary ** operator for Groovy
     *
     * @param exponent exponent
     * @return power
     * @since 0.0.1
     */
    public final @NotNull T power(final int exponent) {
        return pow(exponent);
    }

    /**
     * Numerator
     *
     * @return numerator
     * @since 0.0.1
     */
    public final @NotNull N getNumerator() {
        return numerator;
    }

    /**
     * Denominator
     *
     * @return denominator
     * @since 0.0.1
     */
    public final @NotNull N getDenominator() {
        return denominator;
    }

    @Override
    public final int hashCode() {
        return hash(numerator, denominator);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (AbstractFraction<?, ?, ?>) obj;
        return numerator.equals(other.getNumerator()) && denominator.equals(other.getDenominator());
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{numerator=" + numerator + ", denominator=" + denominator + "}";
    }
}

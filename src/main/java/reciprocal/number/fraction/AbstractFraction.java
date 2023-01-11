package reciprocal.number.fraction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reciprocal.linear.field.QuotientField;

/**
 * Base class for fractions
 *
 * @param <N> {@link Number}
 * @param <T> {@link AbstractFraction}
 * @param <Q> type of quotient of N
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractFraction<N extends Number, T extends AbstractFraction<N, T, Q>, Q extends Number>
    extends Number
    implements Comparable<T> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final @NotNull N numerator;
    private final @NotNull N denominator;

    /**
     * All arguments Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws NullPointerException when {@code numerator == null}
     * @throws NullPointerException when {@code denominator == null}
     */
    protected AbstractFraction(final @NotNull N numerator, final @NotNull N denominator) {
        this.numerator = requireNonNull(numerator, "numerator");
        this.denominator = requireNonNull(denominator, "denominator");
    }

    /**
     * Indicates if this is invertible
     *
     * @return {@link Boolean}
     */
    public final boolean isInvertible() {
        return !quotientField().getEqualityByComparing().test(numerator, quotientField().getZero());
    }

    /**
     * Indicates if this is not invertible
     *
     * @return {@link Boolean}
     */
    public final boolean isNotInvertible() {
        return !isInvertible();
    }

    /**
     * Indicates if this is a unit
     *
     * @return {@link Boolean}
     */
    public final boolean isUnit() {
        return quotientField().getEqualityByComparing().test(numerator, quotientField().getOne());
    }

    /**
     * Indicates if this is not a unit
     *
     * @return {@link Boolean}
     */
    public final boolean isNotUnit() {
        return !isUnit();
    }

    /**
     * Indicates if this is dyadic
     *
     * @return {@link Boolean}
     */
    public abstract boolean isDyadic();

    /**
     * Indicates if this is not dyadic
     *
     * @return {@link Boolean}
     */
    public final boolean isNotDyadic() {
        return !isDyadic();
    }

    /**
     * Indicates if this is irreducible
     *
     * @return {@link Boolean}
     */
    public abstract boolean isIrreducible();

    /**
     * Indicates if this is not reducible
     *
     * @return {@link Boolean}
     */
    public final boolean isReducible() {
        return !isIrreducible();
    }

    /**
     * Indicates if this is proper
     *
     * @return {@link Boolean}
     */
    public abstract boolean isProper();

    /**
     * Indicates if this is improper
     *
     * @return {@link Boolean}
     */
    public final boolean isImproper() {
        return !isProper();
    }

    /**
     * Signum
     *
     * @return signum
     */
    public abstract int getSignum();

    /**
     * Returns the sum of this and the summand
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     */
    public final @NotNull T add(final @NotNull T summand) {
        requireNonNull(summand, "summand");
        final var num = quotientField().getAddition().apply(
            quotientField().getMultiplication().apply(summand.getDenominator(), numerator),
            quotientField().getMultiplication().apply(denominator, summand.getNumerator()));
        final var den = quotientField().getMultiplication().apply(denominator, summand.getDenominator());
        return constructor().apply(num, den);
    }

    /**
     * Returns the difference of this and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     */
    public final @NotNull T subtract(final @NotNull T subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var num = quotientField().getSubtraction().apply(
            quotientField().getMultiplication().apply(subtrahend.getDenominator(), numerator),
            quotientField().getMultiplication().apply(denominator, subtrahend.getNumerator()));
        final var den = quotientField().getMultiplication().apply(denominator, subtrahend.getDenominator());
        return constructor().apply(num, den);
    }

    /**
     * Returns the product of this and the factor
     *
     * @param factor factor
     * @return product
     * @throws NullPointerException when {@code factor == null}
     */
    public final @NotNull T multiply(final @NotNull T factor) {
        requireNonNull(factor, "factor");
        final var num = quotientField().getMultiplication().apply(numerator, factor.getNumerator());
        final var den = quotientField().getMultiplication().apply(denominator, factor.getDenominator());
        return constructor().apply(num, den);
    }

    /**
     * Returns the quotient of this and the divisor
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
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
     */
    public final @NotNull T pow(final int exponent) {
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        return one();
    }

    /**
     * Returns the negated
     *
     * @return negated
     */
    public final @NotNull T negate() {
        return constructor().apply(quotientField().getNegation().apply(numerator), denominator);
    }

    /**
     * Returns the inverted
     *
     * @return inverted
     * @throws IllegalStateException if this is not invertible
     */
    public final @NotNull T invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return constructor().apply(denominator, numerator);
    }

    /**
     * Returns the absolute value
     *
     * @return absolute value
     */
    public final @NotNull T abs() {
        final var num = quotientField().getAbsOperator().apply(numerator);
        final var den = quotientField().getAbsOperator().apply(denominator);
        return constructor().apply(num, den);
    }

    /**
     * Returns if this is less than or equal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public abstract boolean lessThanOrEqualTo(@NotNull T other);

    /**
     * Returns if this is greater than or equal to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public final boolean greaterThanOrEqualTo(@NotNull final T other) {
        requireNonNull(other, "other");
        return !lessThanOrEqualTo(other) || equivalent(other);
    }

    /**
     * Returns if this is strictly less than other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public final boolean lessThan(@NotNull final T other) {
        requireNonNull(other, "other");
        return !greaterThanOrEqualTo(other);
    }

    /**
     * Returns if this is strictly greater than other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public final boolean greaterThan(@NotNull final T other) {
        requireNonNull(other, "other");
        return !lessThanOrEqualTo(other);
    }

    /**
     * Returns the minimum of this and other
     *
     * @param other other
     * @return minimum
     * @throws NullPointerException when {@code other == null}
     */
    @SuppressWarnings("unchecked")
    public final @NotNull T min(@NotNull final T other) {
        requireNonNull(other, "other");
        return greaterThan(other) ? other : (T) this;
    }

    /**
     * Returns the maximum of this and other
     *
     * @param other other
     * @return minimum
     * @throws NullPointerException when {@code other == null}
     */
    @SuppressWarnings("unchecked")
    public @NotNull T max(@NotNull final T other) {
        requireNonNull(other, "other");
        return lessThan(other) ? other : (T) this;
    }

    /**
     * Returns this incremented by 1
     *
     * @return increment
     */
    public final @NotNull T inc() {
        return add(one());
    }

    /**
     * Returns this decremented by 1
     *
     * @return decrement
     */
    public final @NotNull T dec() {
        return subtract(one());
    }

    /**
     * Returns the normalized
     *
     * @return normalized
     */
    public abstract @NotNull T normalize();

    /**
     * Returns the reduced
     *
     * @return reduced
     */
    public abstract @NotNull T reduce();

    /**
     * Returns this expanded by number
     *
     * @param number number
     * @return expanded
     * @throws NullPointerException when {@code number == null}
     */
    public final @NotNull T expand(final @NotNull N number) {
        requireNonNull(number, "number");
        final var num = quotientField().getMultiplication().apply(number, numerator);
        final var den = quotientField().getMultiplication().apply(number, denominator);
        return constructor().apply(num, den);
    }

    /**
     * Returns if this is equivalent to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code number == null}
     */
    public final boolean equivalent(@NotNull final T other) {
        requireNonNull(other, "other");
        return normalize().reduce().equals(other.normalize().reduce());
    }

    /**
     * Returns this as {@link BigDecimal}
     *
     * @return {@link BigDecimal}
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
     * Quotient field
     *
     * @return quotient field
     */
    protected abstract @NotNull QuotientField<@NotNull N, @NotNull Q, @NotNull N> quotientField();

    /**
     * 1
     *
     * @return 1
     */
    protected abstract @NotNull T one();

    /**
     * Constructor
     *
     * @return constructor
     */
    protected abstract @NotNull BiFunction<N, N, T> constructor();

    /**
     * Binary + operator for Groovy and Kotlin
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     */
    public final @NotNull T plus(@NotNull final T summand) {
        requireNonNull(summand, "summand");
        return add(summand);
    }

    /**
     * Binary - operator for Groovy and Kotlin
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     */
    public final @NotNull T minus(@NotNull final T subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        return subtract(subtrahend);
    }

    /**
     * Binary / operator for Groovy and Kotlin
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     */
    public final @NotNull T div(@NotNull final T divisor) {
        requireNonNull(divisor, "divisor");
        return divide(divisor);
    }

    /**
     * Binary ** operator for Groovy
     *
     * @param exponent exponent
     * @return power
     */
    public final @NotNull T power(final int exponent) {
        return pow(exponent);
    }

    /**
     * Numerator
     *
     * @return numerator
     */
    public final @NotNull N getNumerator() {
        return numerator;
    }

    /**
     * Denominator
     *
     * @return denominator
     */
    public final @NotNull N getDenominator() {
        return denominator;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(numerator, denominator);
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
        return getClass().getSimpleName() + "(numerator=" + numerator + ", denominator=" + denominator + ")";
    }
}

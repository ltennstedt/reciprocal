package reciprocal.number.complex;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for complex numbers
 *
 * @param <N> type of number
 * @param <T> type of this
 * @param <Q> type of quotient
 * @param <A> type of absolute value
 * @param <P> type of polar form
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractComplex<N extends Number, T extends AbstractComplex<N, T, Q, A, P>, Q, A, P>
        extends Number {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Real part
     *
     * @since 0.0.1
     */
    private final @NotNull N real;

    /**
     * Imaginary part
     *
     * @since 0.0.1
     */
    private final @NotNull N imaginary;

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     * @throws NullPointerException when {@code real == null}
     * @throws NullPointerException when {@code imaginary == null}
     * @since 0.0.1
     */
    protected AbstractComplex(final @NotNull N real, final @NotNull N imaginary) {
        this.real = requireNonNull(real, "real");
        this.imaginary = requireNonNull(imaginary, "imaginary");
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
     * @throws NullPointerException when divisor is not invertible
     * @since 0.0.1
     */
    public abstract @NotNull Q divide(@NotNull T divisor);

    /**
     * Returns the power raised by the exponent
     *
     * @param exponent exponent
     * @return power
     * @since 0.0.1
     */
    public abstract @NotNull Q pow(int exponent);

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
     * @since 0.0.1
     */
    public abstract @NotNull Q invert();

    /**
     * Returns the square of the absolute value
     *
     * @return square of the absolute value
     * @since 0.0.1
     */
    public abstract @NotNull N absPow2();

    /**
     * Returns the absolute value
     *
     * @return absolute value
     * @since 0.0.1
     */
    public abstract @NotNull A abs();

    /**
     * Returns the conjugated
     *
     * @return conjugated
     * @since 0.0.1
     */
    public abstract @NotNull T conjugate();

    /**
     * Returns the argument
     *
     * @return argument
     * @since 0.0.1
     */
    public abstract @NotNull A argument();

    @Override
    public final int intValue() {
        return real.intValue();
    }

    @Override
    public final long longValue() {
        return real.longValue();
    }

    @Override
    public final float floatValue() {
        return real.floatValue();
    }

    @Override
    public final double doubleValue() {
        return real.doubleValue();
    }

    /**
     * Returns this as {@link BigInteger}
     *
     * @return {@link BigInteger}
     * @since 0.0.1
     */
    public abstract @NotNull BigInteger toBigInteger();

    /**
     * Returns this as {@link BigDecimal}
     *
     * @return {@link BigDecimal}
     * @since 0.0.1
     */
    public abstract @NotNull BigDecimal toBigDecimal();

    /**
     * Returns this as polar form
     *
     * @return polar form
     * @since 0.0.1
     */
    public abstract @NotNull P toPolarForm();

    /**
     * Returns if this is equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public abstract boolean equalsByComparing(@NotNull T other);

    /**
     * Returns if this is not equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean doesNotEqualByComparing(final @NotNull T other) {
        requireNonNull(other, "other");
        return !equalsByComparing(other);
    }

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
    public final @NotNull Q div(final @NotNull T divisor) {
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
    public final @NotNull Q power(final int exponent) {
        return pow(exponent);
    }

    /**
     * Real
     *
     * @return real
     * @since 0.0.1
     */
    public final @NotNull N getReal() {
        return real;
    }

    /**
     * Imaginary
     *
     * @return imaginary
     * @since 0.0.1
     */
    public final @NotNull N getImaginary() {
        return imaginary;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AbstractComplex<?, ?, ?, ?, ?> other = (AbstractComplex<?, ?, ?, ?, ?>) obj;
        return real.equals(other.getReal()) && imaginary.equals(other.getImaginary());
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "(real=" + real + ", imaginary=" + imaginary + ")";
    }
}

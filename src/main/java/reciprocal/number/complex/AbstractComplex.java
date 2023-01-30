package reciprocal.number.complex;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Base class for complex numbers
 *
 * @param <N> {@link Number}
 * @param <T> {@link AbstractComplex}
 * @param <Q> quotient
 * @param <A> absolute value
 * @param <P> {@link AbstractPolarForm}
 * @since 0.0.1
 */
public abstract class AbstractComplex<N extends Number, T extends AbstractComplex<N, T, Q, A, P>, Q, A, P>
    extends Number {
    @Serial
    private static final long serialVersionUID = 1L;

    private final @NonNull N real;
    private final @NonNull N imaginary;

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     * @throws NullPointerException when {@code real == null}
     * @throws NullPointerException when {@code imaginary == null}
     * @since 0.0.1
     */
    protected AbstractComplex(final @NonNull N real, final @NonNull N imaginary) {
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
     * Calculates the sum
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @since 0.0.1
     */
    public abstract @NonNull T add(@NonNull T summand);

    /**
     * Calculates the difference
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when {@code subtrahend == null}
     * @since 0.0.1
     */
    public abstract @NonNull T subtract(@NonNull T subtrahend);

    /**
     * Calculates the product
     *
     * @param factor factor
     * @return product
     * @throws NullPointerException when {@code factor == null}
     * @since 0.0.1
     */
    public abstract @NonNull T multiply(@NonNull T factor);

    /**
     * Calculates the quotient
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     * @throws NullPointerException when divisor is not invertible
     * @since 0.0.1
     */
    public abstract @NonNull Q divide(@NonNull T divisor);

    /**
     * Calculates the power
     *
     * @param exponent exponent
     * @return power
     * @since 0.0.1
     */
    public abstract @NonNull Q pow(int exponent);

    /**
     * Calculates the negated
     *
     * @return negated
     * @since 0.0.1
     */
    public abstract @NonNull T negate();

    /**
     * Calculates the inverted
     *
     * @return inverted
     * @since 0.0.1
     */
    public abstract @NonNull Q invert();

    /**
     * Calculates the absolute value
     *
     * @return absolute value
     * @since 0.0.1
     */
    public abstract @NonNull A abs();

    /**
     * Calculates the conjugate
     *
     * @return conjugate
     * @since 0.0.1
     */
    public abstract @NonNull T conjugate();

    /**
     * Calculates the argument
     *
     * @return argument
     * @throws IllegalStateException when this is not invertible
     * @since 0.0.1
     */
    public abstract @NonNull A argument();

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
    public abstract @NonNull BigInteger toBigInteger();

    /**
     * Returns this as {@link BigDecimal}
     *
     * @return {@link BigDecimal}
     * @since 0.0.1
     */
    public abstract @NonNull BigDecimal toBigDecimal();

    /**
     * Returns this as polar form
     *
     * @return polar form
     * @throws IllegalStateException when this is not invertible
     * @since 0.0.1
     */
    public abstract @NonNull P toPolarForm();

    /**
     * Returns if this is equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public abstract boolean equalsByComparing(@NonNull T other);

    /**
     * Returns if this is not equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean doesNotEqualByComparing(final @NonNull T other) {
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
    public final @NonNull T plus(final @NonNull T summand) {
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
    public final @NonNull T minus(final @NonNull T subtrahend) {
        return subtract(subtrahend);
    }

    /**
     * Binary / operator for Groovy and Kotlin
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     * @throws IllegalArgumentException when divisor is not invertible
     * @since 0.0.1
     */
    public final @NonNull Q div(final @NonNull T divisor) {
        return divide(divisor);
    }

    /**
     * Binary ** operator for Groovy
     *
     * @param exponent exponent
     * @return power
     * @since 0.0.1
     */
    public final @NonNull Q power(final int exponent) {
        return pow(exponent);
    }

    /**
     * Calculates the square of the absolute value
     *
     * @return square of the absolute value
     * @since 0.0.1
     */
    protected abstract @NonNull N absPow2();

    /**
     * Real
     *
     * @return real
     * @since 0.0.1
     */
    public final @NonNull N getReal() {
        return real;
    }

    /**
     * Imaginary
     *
     * @return imaginary
     * @since 0.0.1
     */
    public final @NonNull N getImaginary() {
        return imaginary;
    }

    @Override
    public final int hashCode() {
        return hash(real, imaginary);
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
    public final @NonNull String toString() {
        return getClass().getSimpleName() + "{real=" + real + ", imaginary=" + imaginary + "}";
    }
}

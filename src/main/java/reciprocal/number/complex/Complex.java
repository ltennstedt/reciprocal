package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a complex number that uses {@link Double} as
 * type for its real and imaginary part
 *
 * @since 0.0.1
 */
public final class Complex extends AbstractComplex<@NonNull Double, @NonNull Complex, @NonNull Complex,
    @NonNull Double, @NonNull PolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull Complex ZERO = ofReal(0.0D);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull Complex ONE = ofReal(1.0D);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NonNull Complex I = ofImaginary(1.0D);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NonNull Complex MINUS_ONE = ofReal(1.0D);

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NonNull Complex MINUS_I = ofImaginary(-1.0D);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     * @since 0.0.1
     */
    public Complex(final double real, final double imaginary) {
        super(real, imaginary);
    }

    /**
     * Static factory method
     *
     * @param real real part
     * @return real + 0 * i
     * @since 0.0.1
     */
    public static Complex ofReal(final double real) {
        return new Complex(real, 0.0D);
    }

    /**
     * Static factory method
     *
     * @param imaginary imaginary part
     * @return 0 + imaginary * i
     * @since 0.0.1
     */
    public static Complex ofImaginary(final double imaginary) {
        return new Complex(0.0D, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NonNull Complex add(final @NonNull Complex summand) {
        requireNonNull(summand, "summand");
        return new Complex(getReal() + summand.getReal(), getImaginary() + summand.getImaginary());
    }

    @Override
    public @NonNull Complex subtract(final @NonNull Complex subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        return new Complex(getReal() - subtrahend.getReal(), getImaginary() - subtrahend.getImaginary());
    }

    @Override
    public @NonNull Complex multiply(final @NonNull Complex factor) {
        requireNonNull(factor, "factor");
        final var re = getReal() * factor.getReal() - getImaginary() * factor.getImaginary();
        final var im = getReal() * factor.getImaginary() + getImaginary() * factor.getReal();
        return new Complex(re, im);
    }

    @Override
    public @NonNull Complex divide(final @NonNull Complex divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "expected divisor to be invertible but divisor = %s", divisor);
        final var den = Math.pow(divisor.getReal(), 2.0D) + Math.pow(divisor.getImaginary(), 2.0D);
        final var re = (getReal() * divisor.getReal() + getImaginary() * divisor.getImaginary()) / den;
        final var im = (getImaginary() * divisor.getReal() - getReal() * divisor.getImaginary()) / den;
        return new Complex(re, im);
    }

    @Override
    public @NonNull Complex pow(final int exponent) {
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        return ONE;
    }

    @Override
    public @NonNull Complex negate() {
        return new Complex(-getReal(), -getImaginary());
    }

    @Override
    public @NonNull Complex invert() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NonNull Double abs() {
        return Math.sqrt(absPow2());
    }

    @Override
    public @NonNull Complex conjugate() {
        return new Complex(getReal(), -getImaginary());
    }

    @Override
    public @NonNull Double argument() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = Math.acos(getReal() / abs());
        return getImaginary() < 0.0D ? -acos : acos;
    }

    @Override
    public @NonNull BigInteger toBigInteger() {
        return toBigDecimal().toBigInteger();
    }

    /**
     * Returns this as exact {@link BigInteger}
     *
     * @return {@link BigInteger}
     * @throws ArithmeticException when real part is not exact
     * @since 0.0.1
     */
    public @NonNull BigInteger toBigIntegerExact() {
        return toBigDecimal().toBigIntegerExact();
    }

    @Override
    public @NonNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(doubleValue());
    }

    /**
     * Returns this as {@link BigComplex}
     *
     * @return {@link BigComplex}
     */
    public @NonNull BigComplex toBigComplex() {
        return new BigComplex(BigDecimal.valueOf(getReal()), BigDecimal.valueOf(getImaginary()));
    }

    @Override
    public @NonNull PolarForm toPolarForm() {
        return new PolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NonNull Complex other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NonNull Double absPow2() {
        return Math.pow(getReal(), 2.0D) + Math.pow(getImaginary(), 2.0D);
    }
}

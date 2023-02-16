package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a complex number that uses {@link Double} as
 * type for its real and imaginary part
 *
 * @since 0.0.1
 */
public final class Complex extends AbstractComplex<@NotNull Double, @NotNull Complex, @NotNull Complex,
    @NotNull Double, @NotNull PolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NotNull Complex ZERO = ofReal(0.0D);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NotNull Complex ONE = ofReal(1.0D);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NotNull Complex I = ofImaginary(1.0D);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NotNull Complex MINUS_ONE = ofReal(1.0D);

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NotNull Complex MINUS_I = ofImaginary(-1.0D);

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
    public static @NotNull Complex ofReal(final double real) {
        return new Complex(real, 0.0D);
    }

    /**
     * Static factory method
     *
     * @param imaginary imaginary part
     * @return 0 + imaginary * i
     * @since 0.0.1
     */
    public static @NotNull Complex ofImaginary(final double imaginary) {
        return new Complex(0.0D, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NotNull Complex add(final @NotNull Complex summand) {
        requireNonNull(summand, "summand");
        return new Complex(getReal() + summand.getReal(), getImaginary() + summand.getImaginary());
    }

    @Override
    public @NotNull Complex subtract(final @NotNull Complex subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        return new Complex(getReal() - subtrahend.getReal(), getImaginary() - subtrahend.getImaginary());
    }

    @Override
    public @NotNull Complex multiply(final @NotNull Complex factor) {
        requireNonNull(factor, "factor");
        final var re = getReal() * factor.getReal() - getImaginary() * factor.getImaginary();
        final var im = getReal() * factor.getImaginary() + getImaginary() * factor.getReal();
        return new Complex(re, im);
    }

    @Override
    public @NotNull Complex divide(final @NotNull Complex divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "expected divisor to be invertible but divisor = %s", divisor);
        final var den = Math.pow(divisor.getReal(), 2.0D) + Math.pow(divisor.getImaginary(), 2.0D);
        final var re = (getReal() * divisor.getReal() + getImaginary() * divisor.getImaginary()) / den;
        final var im = (getImaginary() * divisor.getReal() - getReal() * divisor.getImaginary()) / den;
        return new Complex(re, im);
    }

    @Override
    public @NotNull Complex pow(final int exponent) {
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        return ONE;
    }

    @Override
    public @NotNull Complex negate() {
        return new Complex(-getReal(), -getImaginary());
    }

    @Override
    public @NotNull Complex invert() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NotNull Double abs() {
        return Math.sqrt(absPow2());
    }

    @Override
    public @NotNull Complex conjugate() {
        return new Complex(getReal(), -getImaginary());
    }

    @Override
    public @NotNull Double argument() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = Math.acos(getReal() / abs());
        return getImaginary() < 0.0D ? -acos : acos;
    }

    @Override
    public @NotNull BigInteger toBigInteger() {
        return toBigDecimal().toBigInteger();
    }

    /**
     * Returns this as exact {@link BigInteger}
     *
     * @return {@link BigInteger}
     * @throws ArithmeticException when real part is not exact
     * @since 0.0.1
     */
    public @NotNull BigInteger toBigIntegerExact() {
        return toBigDecimal().toBigIntegerExact();
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(doubleValue());
    }

    /**
     * Returns this as {@link BigComplex}
     *
     * @return {@link BigComplex}
     */
    public @NotNull BigComplex toBigComplex() {
        return new BigComplex(BigDecimal.valueOf(getReal()), BigDecimal.valueOf(getImaginary()));
    }

    @Override
    public @NotNull PolarForm toPolarForm() {
        return new PolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NotNull Complex other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NotNull Double absPow2() {
        return Math.pow(getReal(), 2.0D) + Math.pow(getImaginary(), 2.0D);
    }
}

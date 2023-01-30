package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a Gaussian integer that uses {@link BigInteger}
 * as type for its real and imaginary part
 *
 * @since 0.0.1
 */
public final class BigGaussian extends AbstractComplex<@NotNull BigInteger, @NotNull BigGaussian, @NotNull BigComplex,
    @NotNull BigDecimal, @NotNull BigPolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian ZERO = ofReal(BigInteger.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian ONE = ofReal(BigInteger.ONE);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian I = ofImaginary(BigInteger.ONE);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian MINUS_ONE = ONE.negate();

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian MINUS_I = I.negate();

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final @NotNull Set<@NotNull BigGaussian> UNITS = Set.of(ONE, I, MINUS_ONE, MINUS_I);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     * @throws NullPointerException when {@code real == null}
     * @throws NullPointerException when {@code imaginary == null}
     * @since 0.0.1
     */
    public BigGaussian(final @NotNull BigInteger real, final @NotNull BigInteger imaginary) {
        super(real, imaginary);
    }

    /**
     * Static factory method
     *
     * @param real real part
     * @return real + 0 * i
     * @throws NullPointerException when {@code real == null}
     * @since 0.0.1
     */
    public static BigGaussian ofReal(final @NotNull BigInteger real) {
        return new BigGaussian(real, BigInteger.ZERO);
    }

    /**
     * Static factory method
     *
     * @param imaginary imaginary part
     * @return 0 + imaginary * i
     * @throws NullPointerException when {@code imaginary == null}
     * @since 0.0.1
     */
    public static BigGaussian ofImaginary(final @NotNull BigInteger imaginary) {
        return new BigGaussian(BigInteger.ZERO, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NotNull BigGaussian add(final @NotNull BigGaussian summand) {
        requireNonNull(summand, "summand");
        final var re = getReal().add(summand.getReal());
        final var im = getImaginary().add(summand.getImaginary());
        return new BigGaussian(re, im);
    }

    @Override
    public @NotNull BigGaussian subtract(final @NotNull BigGaussian subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal().subtract(subtrahend.getReal());
        final var im = getImaginary().subtract(subtrahend.getImaginary());
        return new BigGaussian(re, im);
    }

    @Override
    public @NotNull BigGaussian multiply(final @NotNull BigGaussian factor) {
        requireNonNull(factor, "factor");
        final var re = getReal().multiply(factor.getReal()).subtract(getImaginary().multiply(factor.getImaginary()));
        final var im = getReal().multiply(factor.getImaginary()).add(getImaginary().multiply(factor.getReal()));
        return new BigGaussian(re, im);
    }

    @Override
    public @NotNull BigComplex divide(final @NotNull BigGaussian divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "divisor expected to be invertible but divisor = %s", divisor);
        final var den = new BigDecimal(divisor.getReal().pow(2).add(divisor.getImaginary().pow(2)));
        final var re = new BigDecimal(
            getReal().multiply(divisor.getReal()).add(getImaginary().multiply(divisor.getImaginary())))
            .divide(den, MathContext.DECIMAL128);
        final var im = new BigDecimal(
            getImaginary().multiply(divisor.getReal()).subtract(getReal().multiply(divisor.getImaginary())))
            .divide(den, MathContext.DECIMAL128);
        return new BigComplex(re, im);
    }

    /**
     * Calculates the quotient
     *
     * @param divisor divisor
     * @param mathContext {@link MathContext}
     * @return quotient
     * @throws NullPointerException when {@code divisor == null}
     * @throws NullPointerException when divisor is not invertible
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex divide(final @NotNull BigGaussian divisor, final @NotNull MathContext mathContext) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "divisor expected to be invertible but divisor = %s", divisor);
        requireNonNull(mathContext, "mathContext");
        final var den = new BigDecimal(divisor.getReal().pow(2).add(divisor.getImaginary().pow(2)));
        final var re = new BigDecimal(
            getReal().multiply(divisor.getReal()).add(getImaginary().multiply(divisor.getImaginary())))
            .divide(den, mathContext);
        final var im = new BigDecimal(
            getImaginary().multiply(divisor.getReal()).subtract(getReal().multiply(divisor.getImaginary())))
            .divide(den, mathContext);
        return new BigComplex(re, im);
    }

    @Override
    public @NotNull BigComplex pow(final int exponent) {
        if (exponent < 0) {
            return toBigComplex().multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return toBigComplex().multiply(pow(exponent - 1));
        }
        return BigComplex.ONE;
    }

    /**
     * Calculates the power
     *
     * @param exponent exponent
     * @param mathContext {@link MathContext}
     * @return power
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex pow(final int exponent, final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        if (exponent < 0) {
            return toBigComplex().multiply(pow(-exponent - 1, mathContext), mathContext).invert(mathContext);
        }
        if (exponent > 0) {
            return toBigComplex().multiply(pow(exponent - 1, mathContext), mathContext);
        }
        return BigComplex.ONE;
    }

    @Override
    public @NotNull BigGaussian negate() {
        return new BigGaussian(getReal().negate(), getImaginary().negate());
    }

    @Override
    public @NotNull BigComplex invert() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    /**
     * Calculates the inverted
     *
     * @param mathContext {@link MathContext}
     * @return inverted
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex invert(final @NotNull MathContext mathContext) {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        requireNonNull(mathContext, "mathContext");
        return ONE.divide(this, mathContext);
    }

    @Override
    public @NotNull BigDecimal abs() {
        return new BigDecimal(absPow2()).sqrt(MathContext.DECIMAL128);
    }

    /**
     * Calculates the absolute value
     *
     * @param mathContext {@link MathContext}
     * @return absolute value
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigDecimal abs(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigDecimal(absPow2()).sqrt(mathContext);
    }

    @Override
    public @NotNull BigGaussian conjugate() {
        return new BigGaussian(getReal(), getImaginary().negate());
    }

    @Override
    public @NotNull BigDecimal argument() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = BigDecimalMath.acos(new BigDecimal(getReal()).divide(abs(), MathContext.DECIMAL128),
            MathContext.DECIMAL128);
        return getImaginary().compareTo(BigInteger.ZERO) < 0 ? acos.negate() : acos;
    }

    /**
     * Calculates the argument
     *
     * @param mathContext {@link MathContext}
     * @return argument
     * @throws IllegalStateException when this is not invertible
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigDecimal argument(final @NotNull MathContext mathContext) {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        requireNonNull(mathContext, "mathContext");
        final var acos =
            BigDecimalMath.acos(new BigDecimal(getReal()).divide(abs(mathContext), mathContext), mathContext);
        return getImaginary().compareTo(BigInteger.ZERO) < 0 ? acos.negate(mathContext) : acos;
    }

    @Override
    public @NotNull BigInteger toBigInteger() {
        return getReal();
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return new BigDecimal(getReal());
    }

    /**
     * Returns this as {@link BigComplex}
     *
     * @return {@link BigComplex}
     * @since 0.0.1
     */
    public @NotNull BigComplex toBigComplex() {
        return new BigComplex(new BigDecimal(getReal()), new BigDecimal(getImaginary()));
    }

    @Override
    public @NotNull BigPolarForm toPolarForm() {
        return new BigPolarForm(abs(), argument());
    }

    /**
     * Returns this as polar form
     *
     * @param mathContext {@link MathContext}
     * @return polar form
     * @throws IllegalStateException when this is not invertible
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigPolarForm toPolarForm(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigPolarForm(abs(mathContext), argument(mathContext));
    }

    @Override
    public boolean equalsByComparing(final @NotNull BigGaussian other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NotNull BigInteger absPow2() {
        return getReal().pow(2).add(getImaginary().pow(2));
    }
}

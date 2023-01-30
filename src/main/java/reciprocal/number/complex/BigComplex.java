package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a complex number that uses {@link BigDecimal} as
 * type for its real and imaginary part
 *
 * @since 0.0.1
 */
public final class BigComplex extends AbstractComplex<@NotNull BigDecimal, @NotNull BigComplex, @NotNull BigComplex,
    @NotNull BigDecimal, @NotNull BigPolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NotNull BigComplex ZERO = ofReal(BigDecimal.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NotNull BigComplex ONE = ofReal(BigDecimal.ONE);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NotNull BigComplex I = ofImaginary(BigDecimal.ONE);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NotNull BigComplex MINUS_ONE = ONE.negate();

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NotNull BigComplex MINUS_I = I.negate();

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
    public BigComplex(final @NotNull BigDecimal real, final @NotNull BigDecimal imaginary) {
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
    public static BigComplex ofReal(final @NotNull BigDecimal real) {
        return new BigComplex(real, BigDecimal.ZERO);
    }

    /**
     * Static factory method
     *
     * @param imaginary imaginary part
     * @return 0 + imaginary * i
     * @throws NullPointerException when {@code imaginary == null}
     * @since 0.0.1
     */
    public static BigComplex ofImaginary(final @NotNull BigDecimal imaginary) {
        return new BigComplex(BigDecimal.ZERO, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NotNull BigComplex add(final @NotNull BigComplex summand) {
        requireNonNull(summand, "summand");
        return new BigComplex(getReal().add(summand.getReal()), getImaginary().add(summand.getImaginary()));
    }

    /**
     * Calculates the sum
     *
     * @param summand summand
     * @param mathContext {@link MathContext}
     * @return sum
     * @throws NullPointerException when {@code summand == null}
     * @throws NullPointerException when {@code mathContext == null}
     */
    public @NotNull BigComplex add(final @NotNull BigComplex summand, final @NotNull MathContext mathContext) {
        requireNonNull(summand, "summand");
        requireNonNull(mathContext, "mathContext");
        final var re = getReal().add(summand.getReal(), mathContext);
        final var im = getImaginary().add(summand.getImaginary(), mathContext);
        return new BigComplex(re, im);
    }

    @Override
    public @NotNull BigComplex subtract(final @NotNull BigComplex subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal().subtract(subtrahend.getReal());
        final var im = getImaginary().subtract(subtrahend.getImaginary());
        return new BigComplex(re, im);
    }

    /**
     * Calculates the difference
     *
     * @param subtrahend subtrahend
     * @param mathContext {@link MathContext}
     * @return sum
     * @throws NullPointerException when {@code subtrahend == null}
     * @throws NullPointerException when {@code mathContext == null}
     */
    public @NotNull BigComplex subtract(final @NotNull BigComplex subtrahend, final @NotNull MathContext mathContext) {
        requireNonNull(subtrahend, "subtrahend");
        requireNonNull(mathContext, "mathContext");
        final var re = getReal().subtract(subtrahend.getReal(), mathContext);
        final var im = getImaginary().subtract(subtrahend.getImaginary(), mathContext);
        return new BigComplex(re, im);
    }

    @Override
    public @NotNull BigComplex multiply(final @NotNull BigComplex factor) {
        requireNonNull(factor, "factor");
        final var re = getReal().multiply(factor.getReal()).subtract(getImaginary().multiply(factor.getImaginary()));
        final var im = getReal().multiply(factor.getImaginary()).add(getImaginary().multiply(factor.getReal()));
        return new BigComplex(re, im);
    }

    /**
     * Calculates the product
     *
     * @param factor factor
     * @param mathContext {@link MathContext}
     * @return product
     * @throws NullPointerException when {@code factor == null}
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex multiply(final @NotNull BigComplex factor, final @NotNull MathContext mathContext) {
        requireNonNull(factor, "factor");
        requireNonNull(mathContext, "mathContext");
        final var re = getReal().multiply(factor.getReal(), mathContext)
            .subtract(getImaginary().multiply(factor.getImaginary(), mathContext), mathContext);
        final var im = getReal().multiply(factor.getImaginary(), mathContext)
            .add(getImaginary().multiply(factor.getReal(), mathContext), mathContext);
        return new BigComplex(re, im);
    }

    @Override
    public @NotNull BigComplex divide(final @NotNull BigComplex divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "divisor expected to be invertible but divisor = %s", divisor);
        final var den = divisor.getReal().pow(2).add(divisor.getImaginary().pow(2));
        final var re = getReal().multiply(divisor.getReal()).add(getImaginary().multiply(divisor.getImaginary()))
            .divide(den, MathContext.DECIMAL128);
        final var im = getImaginary().multiply(divisor.getReal()).subtract(getReal().multiply(divisor.getImaginary()))
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
     * @throws IllegalArgumentException when divisor is not invertible
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex divide(final @NotNull BigComplex divisor, final @NotNull MathContext mathContext) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "divisor expected to be invertible but divisor = %s", divisor);
        requireNonNull(mathContext, "mathContext");
        final var den =
            divisor.getReal().pow(2, mathContext).add(divisor.getImaginary().pow(2, mathContext), mathContext);
        final var re = getReal().multiply(divisor.getReal(), mathContext)
            .add(getImaginary().multiply(divisor.getImaginary(), mathContext), mathContext).divide(den, mathContext);
        final var im = getImaginary().multiply(divisor.getReal(), mathContext)
            .subtract(getReal().multiply(divisor.getImaginary(), mathContext), mathContext).divide(den, mathContext);
        return new BigComplex(re, im);
    }

    @Override
    public @NotNull BigComplex pow(final int exponent) {
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        return ONE;
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
            return multiply(pow(-exponent - 1, mathContext), mathContext).invert(mathContext);
        }
        if (exponent > 0) {
            return multiply(pow(exponent - 1, mathContext), mathContext);
        }
        return ONE;
    }

    @Override
    public @NotNull BigComplex negate() {
        return new BigComplex(getReal().negate(), getImaginary().negate());
    }

    /**
     * Calculates the negated
     *
     * @param mathContext {@link MathContext}
     * @return negated
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex negate(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigComplex(getReal().negate(mathContext), getImaginary().negate(mathContext));
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
        return absPow2().sqrt(MathContext.DECIMAL128);
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
        return absPow2(mathContext).sqrt(mathContext);
    }

    @Override
    public @NotNull BigComplex conjugate() {
        return new BigComplex(getReal(), getImaginary().negate());
    }

    /**
     * Calculates the conjugate
     *
     * @param mathContext {@link MathContext}
     * @return conjugate
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigComplex conjugate(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return new BigComplex(getReal(), getImaginary().negate(mathContext));
    }

    @Override
    public @NotNull BigDecimal argument() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = BigDecimalMath.acos(getReal().divide(abs(), MathContext.DECIMAL128), MathContext.DECIMAL128);
        return getImaginary().compareTo(BigDecimal.ZERO) < 0 ? acos.negate() : acos;
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
        final var acos = BigDecimalMath.acos(getReal().divide(abs(), mathContext), mathContext);
        return getImaginary().compareTo(BigDecimal.ZERO) < 0 ? acos.negate(mathContext) : acos;
    }

    @Override
    public @NotNull BigInteger toBigInteger() {
        return getReal().toBigInteger();
    }

    /**
     * Returns this as exact {@link BigInteger}
     *
     * @return {@link BigInteger}
     * @throws ArithmeticException when real part is not an exact {@link BigInteger}
     * @since 0.0.1
     */
    public @NotNull BigInteger toBigIntegerExact() {
        return getReal().toBigIntegerExact();
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return getReal();
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
        return new BigPolarForm(abs(mathContext), argument(mathContext));
    }

    @Override
    public boolean equalsByComparing(final @NotNull BigComplex other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NotNull BigDecimal absPow2() {
        return getReal().pow(2).add(getImaginary().pow(2));
    }

    private BigDecimal absPow2(final MathContext mathContext) {
        return getReal().pow(2, mathContext).add(getImaginary().pow(2, mathContext), mathContext);
    }
}

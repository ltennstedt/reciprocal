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
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a Gaussian integer that uses {@link BigInteger}
 * as type for its real and imaginary part
 *
 * @since 0.0.1
 */
public final class BigGaussian extends AbstractComplex<@NonNull BigInteger, @NonNull BigGaussian, @NonNull BigComplex,
    @NonNull BigDecimal, @NonNull BigPolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull BigGaussian ZERO = ofReal(BigInteger.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull BigGaussian ONE = ofReal(BigInteger.ONE);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NonNull BigGaussian I = ofImaginary(BigInteger.ONE);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NonNull BigGaussian MINUS_ONE = ONE.negate();

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NonNull BigGaussian MINUS_I = I.negate();

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final @NonNull Set<@NonNull BigGaussian> UNITS = Set.of(ONE, I, MINUS_ONE, MINUS_I);

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
    public BigGaussian(final @NonNull BigInteger real, final @NonNull BigInteger imaginary) {
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
    public static BigGaussian ofReal(final @NonNull BigInteger real) {
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
    public static BigGaussian ofImaginary(final @NonNull BigInteger imaginary) {
        return new BigGaussian(BigInteger.ZERO, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NonNull BigGaussian add(final @NonNull BigGaussian summand) {
        requireNonNull(summand, "summand");
        final var re = getReal().add(summand.getReal());
        final var im = getImaginary().add(summand.getImaginary());
        return new BigGaussian(re, im);
    }

    @Override
    public @NonNull BigGaussian subtract(final @NonNull BigGaussian subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal().subtract(subtrahend.getReal());
        final var im = getImaginary().subtract(subtrahend.getImaginary());
        return new BigGaussian(re, im);
    }

    @Override
    public @NonNull BigGaussian multiply(final @NonNull BigGaussian factor) {
        requireNonNull(factor, "factor");
        final var re = getReal().multiply(factor.getReal()).subtract(getImaginary().multiply(factor.getImaginary()));
        final var im = getReal().multiply(factor.getImaginary()).add(getImaginary().multiply(factor.getReal()));
        return new BigGaussian(re, im);
    }

    @Override
    public @NonNull BigComplex divide(final @NonNull BigGaussian divisor) {
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

    @Override
    public @NonNull BigComplex pow(final int exponent) {
        if (exponent < 0) {
            return toBigComplex().multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return toBigComplex().multiply(pow(exponent - 1));
        }
        return BigComplex.ZERO;
    }

    @Override
    public @NonNull BigGaussian negate() {
        return new BigGaussian(getReal().negate(), getImaginary().negate());
    }

    @Override
    public @NonNull BigComplex invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NonNull BigDecimal abs() {
        return new BigDecimal(absPow2()).sqrt(MathContext.DECIMAL128);
    }

    @Override
    public @NonNull BigGaussian conjugate() {
        return new BigGaussian(getReal(), getImaginary().negate());
    }

    @Override
    public @NonNull BigDecimal argument() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = BigDecimalMath.acos(new BigDecimal(getReal()).divide(abs(), MathContext.DECIMAL128),
            MathContext.DECIMAL128);
        return getImaginary().compareTo(BigInteger.ZERO) < 0 ? acos.negate() : acos;
    }

    @Override
    public @NonNull BigInteger toBigInteger() {
        return BigInteger.valueOf(longValue());
    }

    @Override
    public @NonNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(doubleValue());
    }

    /**
     * Returns this as {@link BigComplex}
     *
     * @return {@link BigComplex}
     * @since 0.0.1
     */
    public @NonNull BigComplex toBigComplex() {
        return new BigComplex(new BigDecimal(getReal()), new BigDecimal(getImaginary()));
    }

    @Override
    public @NonNull BigPolarForm toPolarForm() {
        checkState(isInvertible(), "this expected to be invertible but this = %s", this);
        return new BigPolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NonNull BigGaussian other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NonNull BigInteger absPow2() {
        return getReal().pow(2).add(getImaginary().pow(2));
    }
}

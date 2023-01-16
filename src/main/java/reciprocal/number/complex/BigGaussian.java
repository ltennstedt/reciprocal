package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Set;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a Gaussian integer which uses {@link BigInteger}
 * as type for its real and imaginary part
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigGaussian extends AbstractComplex<BigInteger, BigGaussian, BigComplex, BigDecimal, BigPolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian ZERO = new BigGaussian(BigInteger.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian ONE = new BigGaussian(BigInteger.ONE);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NotNull BigGaussian IMAGINARY = new BigGaussian(BigInteger.ZERO, BigInteger.ONE);

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
    public static final @NotNull BigGaussian MINUS_IMAGINARY = IMAGINARY.negate();

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final @NotNull Set<@NotNull BigGaussian> UNITS = Set.of(ONE, IMAGINARY, MINUS_ONE, MINUS_IMAGINARY);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param real real part
     * @throws NullPointerException when {@code real == null}
     * @since 0.0.1
     */
    public BigGaussian(final @NotNull BigInteger real) {
        this(real, BigInteger.ZERO);
    }

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
        checkArgument(divisor.isInvertible(), "expected divisor to be invertible but divisor = %s", divisor);
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
    public @NotNull BigComplex pow(final int exponent) {
        if (exponent < 0) {
            return toBigComplex().multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return toBigComplex().multiply(pow(exponent - 1));
        }
        return BigComplex.ZERO;
    }

    @Override
    public @NotNull BigGaussian negate() {
        return new BigGaussian(getReal().negate(), getImaginary().negate());
    }

    @Override
    public @NotNull BigComplex invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NotNull BigInteger absPow2() {
        return getReal().pow(2).add(getImaginary().pow(2));
    }

    @Override
    public @NotNull BigDecimal abs() {
        return new BigDecimal(absPow2()).sqrt(MathContext.DECIMAL128);
    }

    @Override
    public @NotNull BigGaussian conjugate() {
        return new BigGaussian(getReal(), getImaginary().negate());
    }

    @Override
    public @NotNull BigDecimal argument() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = BigDecimalMath.acos(new BigDecimal(getReal()).divide(abs(), MathContext.DECIMAL128),
            MathContext.DECIMAL128);
        return getImaginary().compareTo(BigInteger.ZERO) < 0 ? acos.negate() : acos;
    }

    @Override
    public @NotNull BigInteger toBigInteger() {
        return BigInteger.valueOf(longValue());
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(doubleValue());
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
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return new BigPolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NotNull BigGaussian other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }
}

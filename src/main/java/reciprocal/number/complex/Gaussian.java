package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import com.google.common.math.LongMath;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a Gaussian integer that uses {@link Long} as
 * type for its real and imaginary part
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Gaussian extends AbstractComplex<@NonNull Long, @NonNull Gaussian, @NonNull Complex,
    @NonNull Double, @NonNull PolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull Gaussian ZERO = ofReal(0L);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull Gaussian ONE = ofReal(1L);

    /**
     * i
     *
     * @since 0.0.1
     */
    public static final @NonNull Gaussian IMAGINARY = ofImaginary(1L);

    /**
     * -1
     *
     * @since 0.0.1
     */
    public static final @NonNull Gaussian MINUS_ONE = ONE.negate();

    /**
     * -i
     *
     * @since 0.0.1
     */
    public static final @NonNull Gaussian MINUS_IMAGINARY = IMAGINARY.negate();

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final @NonNull Set<@NonNull Gaussian> UNITS = Set.of(ONE, IMAGINARY, MINUS_ONE, MINUS_IMAGINARY);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     * @since 0.0.1
     */
    public Gaussian(final long real, final long imaginary) {
        super(real, imaginary);
    }

    /**
     * Static factory method
     *
     * @param real real part
     * @return real + 0 * i
     * @since 0.0.1
     */
    public static @NonNull Gaussian ofReal(final long real) {
        return new Gaussian(real, 0L);
    }

    /**
     * Static factory method
     *
     * @param imaginary imaginary part
     * @return 0 + imaginary * i
     * @since 0.0.1
     */
    public static @NonNull Gaussian ofImaginary(final long imaginary) {
        return new Gaussian(0L, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NonNull Gaussian add(final @NonNull Gaussian summand) {
        requireNonNull(summand, "summand");
        final var re = getReal() + summand.getReal();
        final var im = getImaginary() + summand.getImaginary();
        return new Gaussian(re, im);
    }

    @Override
    public @NonNull Gaussian subtract(final @NonNull Gaussian subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal() - subtrahend.getReal();
        final var im = getImaginary() - subtrahend.getImaginary();
        return new Gaussian(re, im);
    }

    @Override
    public @NonNull Gaussian multiply(final @NonNull Gaussian factor) {
        requireNonNull(factor, "factor");
        final var re = getReal() * factor.getReal() - getImaginary() * factor.getImaginary();
        final var im = getReal() * factor.getImaginary() + getImaginary() * factor.getReal();
        return new Gaussian(re, im);
    }

    @Override
    public @NonNull Complex divide(final @NonNull Gaussian divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "divisor expected to be invertible but divisor = %s", divisor);
        final var den = Math.pow(divisor.getReal(), 2.0D) + Math.pow(divisor.getImaginary(), 2.0D);
        final var re = (getReal() + divisor.getReal() + getImaginary() * divisor.getImaginary()) / den;
        final var im = (getImaginary() * divisor.getReal() - getReal() * divisor.getImaginary()) / den;
        return new Complex(re, im);
    }

    @Override
    public @NonNull Complex pow(final int exponent) {
        if (exponent < 0) {
            return toComplex().multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return toComplex().multiply(pow(exponent - 1));
        }
        return Complex.ZERO;
    }

    @Override
    public @NonNull Gaussian negate() {
        return new Gaussian(-getReal(), -getImaginary());
    }

    @Override
    public @NonNull Complex invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NonNull Double abs() {
        return Math.sqrt(absPow2());
    }

    @Override
    public @NonNull Gaussian conjugate() {
        return new Gaussian(getReal(), -getImaginary());
    }

    @Override
    public @NonNull Double argument() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = Math.acos(getReal() / abs());
        return getImaginary() < 0.0D ? -acos : acos;
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
     * Returns this as {@link Complex}
     *
     * @return {@link Complex}
     * @since 0.0.1
     */
    public @NonNull Complex toComplex() {
        return new Complex(getReal(), getImaginary());
    }

    /**
     * Returns this as {@link BigGaussian}
     *
     * @return {@link BigGaussian}
     * @since 0.0.1
     */
    public @NonNull BigGaussian toBigGaussian() {
        return new BigGaussian(BigInteger.valueOf(getReal()), BigInteger.valueOf(getImaginary()));
    }

    /**
     * Returns this as {@link BigComplex}
     *
     * @return {@link BigComplex}
     * @since 0.0.1
     */
    public @NonNull BigComplex toBigComplex() {
        return new BigComplex(BigDecimal.valueOf(getReal()), BigDecimal.valueOf(getImaginary()));
    }

    @Override
    public @NonNull PolarForm toPolarForm() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return new PolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NonNull Gaussian other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }

    @Override
    protected @NonNull Long absPow2() {
        return LongMath.pow(getReal(), 2) + LongMath.pow(getImaginary(), 2);
    }
}

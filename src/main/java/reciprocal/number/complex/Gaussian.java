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
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a Gaussian integer which uses {@link Long} as type for its real and imaginary part
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Gaussian extends AbstractComplex<Long, Gaussian, Complex, Double, PolarForm> {
    /**
     * 0
     */
    public static final @NotNull Gaussian ZERO = new Gaussian(0L);

    /**
     * 1
     */
    public static final @NotNull Gaussian ONE = new Gaussian(1L);

    /**
     * i
     */
    public static final @NotNull Gaussian IMAGINARY = new Gaussian(0L, 1L);

    /**
     * -1
     */
    public static final @NotNull Gaussian MINUS_ONE = ONE.negate();

    /**
     * -i
     */
    public static final @NotNull Gaussian MINUS_IMAGINARY = IMAGINARY.negate();

    /**
     * Units
     */
    public static final @NotNull Set<@NotNull Gaussian> UNITS = Set.of(ONE, IMAGINARY, MINUS_ONE, MINUS_IMAGINARY);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param real real part
     */
    public Gaussian(final int real) {
        this((long) real);
    }

    /**
     * Constructor
     *
     * @param real real part
     */
    public Gaussian(final long real) {
        this(real, 0L);
    }

    /**
     * Constructor
     *
     * @param real real part
     * @param imaginary imaginary part
     */
    public Gaussian(final long real, final long imaginary) {
        super(real, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NotNull Gaussian add(final @NotNull Gaussian summand) {
        requireNonNull(summand, "summand");
        final var re = getReal() + summand.getReal();
        final var im = getImaginary() + summand.getImaginary();
        return new Gaussian(re, im);
    }

    @Override
    public @NotNull Gaussian subtract(final @NotNull Gaussian subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal() - subtrahend.getReal();
        final var im = getImaginary() - subtrahend.getImaginary();
        return new Gaussian(re, im);
    }

    @Override
    public @NotNull Gaussian multiply(final @NotNull Gaussian factor) {
        requireNonNull(factor, "factor");
        final var re = getReal() * factor.getReal() - getImaginary() * factor.getImaginary();
        final var im = getReal() * factor.getImaginary() + getImaginary() * factor.getReal();
        return new Gaussian(re, im);
    }

    @Override
    public @NotNull Complex divide(final @NotNull Gaussian divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "expected divisor to be invertible but divisor = %s", divisor);
        final var den = Math.pow(divisor.getReal(), 2.0D) + Math.pow(divisor.getImaginary(), 2.0D);
        final var re = (getReal() + divisor.getReal() + getImaginary() * divisor.getImaginary()) / den;
        final var im = (getImaginary() * divisor.getReal() - getReal() * divisor.getImaginary()) / den;
        return new Complex(re, im);
    }

    @Override
    public @NotNull Complex pow(final int exponent) {
        if (exponent < 0) {
            return toComplex().multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return toComplex().multiply(pow(exponent - 1));
        }
        return Complex.ZERO;
    }

    @Override
    public @NotNull Gaussian negate() {
        return new Gaussian(-getReal(), -getImaginary());
    }

    @Override
    public @NotNull Complex invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NotNull Long absPow2() {
        return LongMath.pow(getReal(), 2) + LongMath.pow(getImaginary(), 2);
    }

    @Override
    public @NotNull Double abs() {
        return Math.sqrt(absPow2());
    }

    @Override
    public @NotNull Gaussian conjugate() {
        return new Gaussian(getReal(), -getImaginary());
    }

    @Override
    public @NotNull Double argument() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = Math.acos(getReal() / abs());
        return getImaginary() < 0.0D ? -acos : acos;
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
     * Returns this as {@link Complex}
     *
     * @return {@link Complex}
     */
    public @NotNull Complex toComplex() {
        return new Complex(getReal(), getImaginary());
    }

    /**
     * Returns this as {@link BigGaussian}
     *
     * @return {@link BigGaussian}
     */
    public @NotNull BigGaussian toBigGaussian() {
        return new BigGaussian(BigInteger.valueOf(getReal()), BigInteger.valueOf(getImaginary()));
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
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return new PolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NotNull Gaussian other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }
}

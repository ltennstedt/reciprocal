package reciprocal.number.complex;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a complex number which uses {@link BigDecimal} as
 * type for its real and imaginary part
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigComplex extends AbstractComplex<BigDecimal, BigComplex, BigComplex, BigDecimal, BigPolarForm> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull BigComplex ZERO = new BigComplex(BigDecimal.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull BigComplex ONE = new BigComplex(BigDecimal.ONE);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param  real                 real part
     * @throws NullPointerException when {@code real == null}
     * @since                       0.0.1
     */
    public BigComplex(final @NonNull BigDecimal real) {
        this(real, BigDecimal.ZERO);
    }

    /**
     * Constructor
     *
     * @param  real                 real part
     * @param  imaginary            imaginary part
     * @throws NullPointerException when {@code real == null}
     * @throws NullPointerException when {@code imaginary == null}
     * @since                       0.0.1
     */
    public BigComplex(final @NonNull BigDecimal real, final @NonNull BigDecimal imaginary) {
        super(real, imaginary);
    }

    @Override
    public boolean isInvertible() {
        return doesNotEqualByComparing(ZERO);
    }

    @Override
    public @NonNull BigComplex add(final @NonNull BigComplex summand) {
        requireNonNull(summand, "summand");
        final var re = getReal().add(summand.getReal());
        final var im = getImaginary().add(summand.getImaginary());
        return new BigComplex(re, im);
    }

    @Override
    public @NonNull BigComplex subtract(final @NonNull BigComplex subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var re = getReal().subtract(subtrahend.getReal());
        final var im = getImaginary().subtract(subtrahend.getImaginary());
        return new BigComplex(re, im);
    }

    @Override
    public @NonNull BigComplex multiply(final @NonNull BigComplex factor) {
        requireNonNull(factor, "factor");
        final var re = getReal().multiply(factor.getReal()).subtract(getImaginary().multiply(factor.getImaginary()));
        final var im = getReal().multiply(factor.getImaginary()).add(getImaginary().multiply(factor.getReal()));
        return new BigComplex(re, im);
    }

    @Override
    public @NonNull BigComplex divide(final @NonNull BigComplex divisor) {
        requireNonNull(divisor, "divisor");
        checkArgument(divisor.isInvertible(), "expected divisor to be invertible but divisor = %s", divisor);
        final var den = divisor.getReal().pow(2).add(divisor.getImaginary().pow(2));
        final var re = getReal().multiply(divisor.getReal()).add(getImaginary().multiply(divisor.getImaginary()))
                .divide(den, MathContext.DECIMAL128);
        final var im = getImaginary().multiply(divisor.getReal()).subtract(getReal().multiply(divisor.getImaginary()))
                .divide(den, MathContext.DECIMAL128);
        return new BigComplex(re, im);
    }

    @Override
    public @NonNull BigComplex pow(final int exponent) {
        if (exponent < 0) {
            return multiply(pow(-exponent - 1)).invert();
        }
        if (exponent > 0) {
            return multiply(pow(exponent - 1));
        }
        return ZERO;
    }

    @Override
    public @NonNull BigComplex negate() {
        return new BigComplex(getReal().negate(), getImaginary().negate());
    }

    @Override
    public @NonNull BigComplex invert() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return ONE.divide(this);
    }

    @Override
    public @NonNull BigDecimal absPow2() {
        return getReal().pow(2).add(getImaginary().pow(2));
    }

    @Override
    public @NonNull BigDecimal abs() {
        return absPow2().sqrt(MathContext.DECIMAL128);
    }

    @Override
    public @NonNull BigComplex conjugate() {
        return new BigComplex(getReal(), getImaginary().negate());
    }

    @Override
    public @NonNull BigDecimal argument() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        final var acos = BigDecimalMath.acos(getReal().divide(abs(), MathContext.DECIMAL128), MathContext.DECIMAL128);
        return getImaginary().compareTo(BigDecimal.ZERO) < 0 ? acos.negate() : acos;
    }

    @Override
    public @NonNull BigInteger toBigInteger() {
        return BigInteger.valueOf(longValue());
    }

    @Override
    public @NonNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(doubleValue());
    }

    @Override
    public @NonNull BigPolarForm toPolarForm() {
        checkArgument(isInvertible(), "this expected to be invertible but this = %s", this);
        return new BigPolarForm(abs(), argument());
    }

    @Override
    public boolean equalsByComparing(final @NonNull BigComplex other) {
        requireNonNull(other, "other");
        return getReal().compareTo(other.getReal()) == 0 && getImaginary().compareTo(other.getImaginary()) == 0;
    }
}

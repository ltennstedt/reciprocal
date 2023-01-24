package reciprocal.number.fraction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import com.google.common.math.LongMath;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Comparator;
import java.util.function.BiFunction;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import reciprocal.number.complex.Complex;

/**
 * Immutable implementation of a fraction that uses {@link Long} as type for
 * its numerator and denominator
 * <p>
 * The returned Fractions of most methods are neither normalized nor reduced.
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Fraction extends AbstractFraction<@NonNull Long, @NonNull Fraction, @NonNull Double> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull Fraction ZERO = ofNumerator(0L);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull Fraction ONE = ofNumerator(1L);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws IllegalArgumentException when {@code denominator == 0}
     * @since 0.0.1
     */
    public Fraction(final long numerator, final long denominator) {
        super(numerator, denominator);
        checkArgument(denominator != 0L, "denominator != 0 expected but denominator = %s", denominator);
    }

    /**
     * Static factory method
     *
     * @param numerator numerator
     * @return numerator / 1
     * @since 0.0.1
     */
    public static Fraction ofNumerator(final long numerator) {
        return new Fraction(numerator, 1L);
    }

    /**
     * Static factory method
     *
     * @param denominator denominator
     * @return 1 / denominator
     * @since 0.0.1
     */
    public static Fraction ofDenominator(final long denominator) {
        return new Fraction(1L, denominator);
    }

    @Override
    public boolean isInvertible() {
        return getNumerator() != 0L;
    }

    @Override
    public boolean isUnit() {
        return getNumerator() == 1L;
    }

    @Override
    public boolean isDyadic() {
        return LongMath.isPowerOfTwo(getDenominator());
    }

    @Override
    public boolean isIrreducible() {
        return LongMath.gcd(Math.abs(getNumerator()), Math.abs(getDenominator())) == 1L;
    }

    @Override
    public boolean isProper() {
        return Math.abs(getNumerator()) < Math.abs(getDenominator());
    }

    @Override
    public int getSignum() {
        final var numeratorCompareToZero = getNumerator().compareTo(0L);
        final var denominatorCompareToZero = getDenominator().compareTo(0L);
        if (numeratorCompareToZero == 0 || denominatorCompareToZero == 0) {
            return 0;
        }
        if (numeratorCompareToZero != denominatorCompareToZero) {
            return -1;
        }
        return 1;
    }

    @Override
    public @NonNull Fraction add(final @NonNull Fraction summand) {
        requireNonNull(summand, "summand");
        final var num = summand.getDenominator() * getNumerator() + getDenominator() * summand.getNumerator();
        final var den = getDenominator() * summand.getDenominator();
        return new Fraction(num, den);
    }

    @Override
    public @NonNull Fraction subtract(final @NonNull Fraction subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var num = subtrahend.getDenominator() * getNumerator() - getDenominator() * subtrahend.getNumerator();
        final var den = getDenominator() * subtrahend.getDenominator();
        return new Fraction(num, den);
    }

    @Override
    public @NonNull Fraction multiply(final @NonNull Fraction factor) {
        requireNonNull(factor, "factor");
        return new Fraction(getNumerator() * factor.getNumerator(), getDenominator() * factor.getDenominator());
    }

    @Override
    public @NonNull Fraction negate() {
        return new Fraction(-getNumerator(), getDenominator());
    }

    @Override
    public @NonNull Fraction abs() {
        return new Fraction(Math.abs(getNumerator()), Math.abs(getDenominator()));
    }

    @Override
    public @NonNull Fraction expand(final @NonNull Long number) {
        requireNonNull(number, "number");
        return new Fraction(number * getNumerator(), number * getDenominator());
    }

    @Override
    public boolean lessThanOrEqualTo(final @NonNull Fraction other) {
        requireNonNull(other, "other");
        final var normalized = normalize();
        final var normalizedOther = other.normalize();
        final var left = normalizedOther.getDenominator() * normalized.getNumerator();
        final var right = normalized.getDenominator() * normalizedOther.getNumerator();
        return left <= right;
    }

    @Override
    public @NonNull Fraction normalize() {
        if (getSignum() < 0 && getNumerator().compareTo(0L) > 0) {
            return new Fraction(-getNumerator(), Math.abs(getDenominator()));
        }
        if (getSignum() < 0) {
            return this;
        }
        if (getSignum() == 0) {
            return ZERO;
        }
        return abs();
    }

    @Override
    public @NonNull Fraction reduce() {
        final var gcd = LongMath.gcd(getNumerator(), getDenominator());
        return new Fraction(getNumerator() / gcd, getDenominator() / gcd);
    }

    @Override
    public @NonNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(getNumerator()).divide(BigDecimal.valueOf(getDenominator()), MathContext.DECIMAL128);
    }

    /**
     * Returns this as {@link BigFraction}
     *
     * @return {@link BigFraction}
     */
    public @NonNull BigFraction toBigFraction() {
        return new BigFraction(BigInteger.valueOf(getNumerator()), BigInteger.valueOf(getDenominator()));
    }

    /**
     * Returns this as {@link Complex}
     *
     * @return {@link Complex}
     * @since 0.0.1
     */
    public @NonNull Complex toComplex() {
        return Complex.ofReal(doubleValue());
    }

    @Override
    protected @NonNull Fraction getOne() {
        return ONE;
    }

    @Override
    protected @NonNull BiFunction<@NonNull Long, @NonNull Long, @NonNull Fraction> getConstructor() {
        return Fraction::new;
    }

    @Override
    public int compareTo(final @NonNull Fraction o) {
        return FractionComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator
     *
     * @since 0.0.1
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class FractionComparator implements Comparator<Fraction>, Serializable {
        /**
         * Instance
         *
         * @since 0.0.1
         */
        public static final FractionComparator INSTANCE = new FractionComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private FractionComparator() {
        }

        @Override
        public int compare(final @NonNull Fraction o1, final @NonNull Fraction o2) {
            requireNonNull(o1, "o1");
            requireNonNull(o2, "o2");
            if (o1.lessThan(o2)) {
                return -1;
            }
            if (o1.greaterThan(o2)) {
                return 1;
            }
            return 0;
        }
    }
}

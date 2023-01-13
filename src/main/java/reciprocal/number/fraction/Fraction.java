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
import reciprocal.linear.field.LongQuotientField;
import reciprocal.linear.field.QuotientField;
import reciprocal.number.complex.Complex;

/**
 * Immutable implementation of a fraction which uses {@link Long} as type for
 * its numerator and denominator
 * <p>
 * The returned Fractions of most methods are neither normalized nor reduced.
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Fraction extends AbstractFraction<Long, Fraction, Double> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NonNull Fraction ZERO = new Fraction(0L);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NonNull Fraction ONE = new Fraction(1L);

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param numerator numerator
     * @since           0.0.1
     */
    public Fraction(final long numerator) {
        this(numerator, 1L);
    }

    /**
     * Constructor
     *
     * @param  numerator                numerator
     * @param  denominator              denominator
     * @throws IllegalArgumentException when {@code denominator == 0}
     * @since                           0.0.1
     */
    public Fraction(final long numerator, final long denominator) {
        super(numerator, denominator);
        checkArgument(denominator != 0L, "denominator expected not to be 0 but denominator = %s", denominator);
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
     * @since  0.0.1
     */
    public @NonNull Complex toComplex() {
        return new Complex(doubleValue());
    }

    @Override
    public int compareTo(final @NonNull Fraction o) {
        return FractionComparator.INSTANCE.compare(this, o);
    }

    @Override
    protected @NonNull QuotientField<@NonNull Long, @NonNull Double, @NonNull Long> getQuotientField() {
        return LongQuotientField.INSTANCE;
    }

    @Override
    protected @NonNull Fraction getOne() {
        return ONE;
    }

    @Override
    protected @NonNull BiFunction<Long, Long, Fraction> getConstructor() {
        return Fraction::new;
    }

    /**
     * Comparator which rejects null
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

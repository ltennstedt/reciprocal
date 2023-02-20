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
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a fraction that uses {@link Long} as type for
 * its numerator and denominator
 * <p>
 * The returned Fractions of most methods are neither normalized nor reduced.
 *
 * @since 0.0.1
 */
public final class Fraction extends AbstractFraction<@NotNull Long, @NotNull Fraction, @NotNull Double> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final @NotNull Fraction ZERO = ofNumerator(0L);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final @NotNull Fraction ONE = ofNumerator(1L);

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final Stream<Fraction> UNITS = Stream.iterate(ONE, f -> ofDenominator(f.getDenominator() + 1L));

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
    public static @NotNull Fraction ofNumerator(final long numerator) {
        return new Fraction(numerator, 1L);
    }

    /**
     * Static factory method
     *
     * @param denominator denominator
     * @return 1 / denominator
     * @since 0.0.1
     */
    public static @NotNull Fraction ofDenominator(final long denominator) {
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
        if (numeratorCompareToZero == 0) {
            return 0;
        }
        return numeratorCompareToZero == getDenominator().compareTo(0L) ? 1 : -1;
    }

    @Override
    public @NotNull Fraction add(final @NotNull Fraction summand) {
        requireNonNull(summand, "summand");
        final var num = summand.getDenominator() * getNumerator() + getDenominator() * summand.getNumerator();
        final var den = getDenominator() * summand.getDenominator();
        return new Fraction(num, den);
    }

    @Override
    public @NotNull Fraction subtract(final @NotNull Fraction subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var num = subtrahend.getDenominator() * getNumerator() - getDenominator() * subtrahend.getNumerator();
        final var den = getDenominator() * subtrahend.getDenominator();
        return new Fraction(num, den);
    }

    @Override
    public @NotNull Fraction multiply(final @NotNull Fraction factor) {
        requireNonNull(factor, "factor");
        return new Fraction(getNumerator() * factor.getNumerator(), getDenominator() * factor.getDenominator());
    }

    @Override
    public @NotNull Fraction negate() {
        return new Fraction(-getNumerator(), getDenominator());
    }

    @Override
    public @NotNull Fraction abs() {
        return new Fraction(Math.abs(getNumerator()), Math.abs(getDenominator()));
    }

    @Override
    public @NotNull Fraction expand(final @NotNull Long number) {
        requireNonNull(number, "number");
        return new Fraction(number * getNumerator(), number * getDenominator());
    }

    @Override
    public boolean lessThanOrEqualTo(final @NotNull Fraction other) {
        requireNonNull(other, "other");
        final var normalized = normalize();
        final var normalizedOther = other.normalize();
        final var left = normalizedOther.getDenominator() * normalized.getNumerator();
        final var right = normalized.getDenominator() * normalizedOther.getNumerator();
        return left <= right;
    }

    @Override
    public @NotNull Fraction normalize() {
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
    public @NotNull Fraction reduce() {
        final var gcd = LongMath.gcd(Math.abs(getNumerator()), Math.abs(getDenominator()));
        return new Fraction(getNumerator() / gcd, getDenominator() / gcd);
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(getNumerator()).divide(BigDecimal.valueOf(getDenominator()), MathContext.DECIMAL128);
    }

    /**
     * Returns this as {@link BigFraction}
     *
     * @return {@link BigFraction}
     */
    public @NotNull BigFraction toBigFraction() {
        return new BigFraction(BigInteger.valueOf(getNumerator()), BigInteger.valueOf(getDenominator()));
    }

    @Override
    protected @NotNull Fraction getOne() {
        return ONE;
    }

    @Override
    protected @NotNull BiFunction<@NotNull Long, @NotNull Long, @NotNull Fraction> getConstructor() {
        return Fraction::new;
    }

    @Override
    public int compareTo(final @NotNull Fraction o) {
        return FractionComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator
     *
     * @since 0.0.1
     */
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
        public int compare(final @NotNull Fraction o1, final @NotNull Fraction o2) {
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

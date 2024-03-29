package reciprocal.number.fraction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import com.google.common.math.BigIntegerMath;
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
 * Immutable implementation of a fraction that uses {@link BigFraction} as type
 * for its numerator and denominator
 * <p>
 * The returned Fractions of most methods are neither normalized nor reduced.
 *
 * @since 0.0.1
 */
public final class BigFraction extends
    AbstractFraction<@NotNull BigInteger, @NotNull BigFraction, @NotNull BigDecimal> {
    /**
     * 0
     *
     * @since 0.0.1
     */
    public static final BigFraction ZERO = ofNumerator(BigInteger.ZERO);

    /**
     * 1
     *
     * @since 0.0.1
     */
    public static final BigFraction ONE = ofNumerator(BigInteger.ONE);

    /**
     * Units
     *
     * @since 0.0.1
     */
    public static final Stream<BigFraction> UNITS =
        Stream.iterate(ONE, bf -> ofDenominator(bf.getDenominator().add(BigInteger.ONE)));

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws NullPointerException when {@code numerator == null}
     * @throws NullPointerException when {@code denominator == null}
     * @throws IllegalArgumentException when {@code denominator == 0}
     * @since 0.0.1
     */
    public BigFraction(final @NotNull BigInteger numerator, final @NotNull BigInteger denominator) {
        super(numerator, denominator);
        checkArgument(denominator.compareTo(BigInteger.ZERO) != 0,
            "denominator expected not to be 0 but denominator = %s", denominator);
    }

    /**
     * Static factory method
     *
     * @param numerator numerator
     * @return numerator / 1
     * @throws NullPointerException when {@code numerator == null}
     * @since 0.0.1
     */
    public static @NotNull BigFraction ofNumerator(final @NotNull BigInteger numerator) {
        return new BigFraction(numerator, BigInteger.ONE);
    }

    /**
     * Static factory method
     *
     * @param denominator denominator
     * @return 1 / denominator
     * @throws NullPointerException when {@code denominator == null}
     * @throws IllegalArgumentException when {@code denominator == 0}
     * @since 0.0.1
     */
    public static @NotNull BigFraction ofDenominator(final @NotNull BigInteger denominator) {
        return new BigFraction(BigInteger.ONE, denominator);
    }

    @Override
    public boolean isInvertible() {
        return getNumerator().compareTo(BigInteger.ZERO) != 0;
    }

    @Override
    public boolean isUnit() {
        return getNumerator().compareTo(BigInteger.ONE) == 0;
    }

    @Override
    public boolean isDyadic() {
        return BigIntegerMath.isPowerOfTwo(getDenominator());
    }

    @Override
    public boolean isIrreducible() {
        return getNumerator().abs().gcd(getDenominator().abs()).compareTo(BigInteger.ONE) == 0;
    }

    @Override
    public boolean isProper() {
        return getNumerator().abs().compareTo(getDenominator().abs()) < 0;
    }

    @Override
    public int getSignum() {
        return getNumerator().signum() * getDenominator().signum();
    }

    @Override
    public @NotNull BigFraction add(final @NotNull BigFraction summand) {
        requireNonNull(summand, "summand");
        final var num =
            summand.getDenominator().multiply(getNumerator())
                .add(getDenominator().multiply(summand.getNumerator()));
        final var den = getDenominator().multiply(summand.getDenominator());
        return new BigFraction(num, den);
    }

    @Override
    public @NotNull BigFraction subtract(final @NotNull BigFraction subtrahend) {
        requireNonNull(subtrahend, "subtrahend");
        final var num = subtrahend.getDenominator().multiply(getNumerator())
            .subtract(getDenominator().multiply(subtrahend.getNumerator()));
        final var den = getDenominator().multiply(subtrahend.getDenominator());
        return new BigFraction(num, den);
    }

    @Override
    public @NotNull BigFraction multiply(final @NotNull BigFraction factor) {
        requireNonNull(factor, "factor");
        return new BigFraction(
            getNumerator().multiply(factor.getNumerator()),
            getDenominator().multiply(factor.getDenominator())
        );
    }

    @Override
    public @NotNull BigFraction negate() {
        return new BigFraction(getNumerator().negate(), getDenominator());
    }

    @Override
    public @NotNull BigFraction abs() {
        return new BigFraction(getNumerator().abs(), getDenominator().abs());
    }

    @Override
    public @NotNull BigFraction expand(final @NotNull BigInteger number) {
        return new BigFraction(number.multiply(getNumerator()), number.multiply(getDenominator()));
    }

    @Override
    public boolean lessThanOrEqualTo(final @NotNull BigFraction other) {
        requireNonNull(other, "other");
        final var normalized = normalize();
        final var normalizedOther = other.normalize();
        final var left = normalizedOther.getDenominator().multiply(normalized.getNumerator());
        final var right = normalized.getDenominator().multiply(normalizedOther.getNumerator());
        return left.compareTo(right) < 1;
    }

    @Override
    public @NotNull BigFraction normalize() {
        if (getSignum() < 0 && getNumerator().compareTo(BigInteger.ZERO) > 0) {
            return new BigFraction(getNumerator().negate(), getDenominator().abs());
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
    public @NotNull BigFraction reduce() {
        final var gcd = getNumerator().gcd(getDenominator()).abs();
        return new BigFraction(getNumerator().divide(gcd), getDenominator().divide(gcd));
    }

    @Override
    public @NotNull BigDecimal toBigDecimal() {
        return new BigDecimal(getNumerator()).divide(new BigDecimal(getDenominator()), MathContext.DECIMAL128);
    }

    @Override
    public int compareTo(final @NotNull BigFraction o) {
        return BigFractionComparator.INSTANCE.compare(this, o);
    }

    @Override
    protected @NotNull BigFraction getOne() {
        return ONE;
    }

    @Override
    protected @NotNull BiFunction<BigInteger, BigInteger, BigFraction> getConstructor() {
        return BigFraction::new;
    }

    /**
     * Comparator
     *
     * @since 0.0.1
     */
    public static final class BigFractionComparator implements Comparator<BigFraction>, Serializable {
        /**
         * Instance
         *
         * @since 0.0.1
         */
        public static final BigFractionComparator INSTANCE = new BigFractionComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private BigFractionComparator() {
        }

        @Override
        public int compare(final @NotNull BigFraction o1, final @NotNull BigFraction o2) {
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

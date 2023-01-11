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
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reciprocal.linear.field.BigIntegerQuotientField;
import reciprocal.linear.field.QuotientField;

/**
 * Immutable implementation of a fraction which uses {@link BigFraction} as type for its numerator and denominator
 * <p>
 * The returned Fractions of most methods are neither normalized nor reduced.
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigFraction extends AbstractFraction<BigInteger, BigFraction, BigDecimal> {
    /**
     * 0
     */
    public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO);

    /**
     * 1
     */
    public static final BigFraction ONE = new BigFraction(BigInteger.ONE);

    /**
     * Units
     */
    public static final Stream<BigFraction> UNITS = Stream.iterate(
        ONE,
        bf -> new BigFraction(BigInteger.ONE, bf.getDenominator().add(BigInteger.ONE)));

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param numerator numerator
     */
    public BigFraction(final int numerator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(1L));
    }

    /**
     * Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws IllegalArgumentException when {@code denominator == 0}
     */
    public BigFraction(final int numerator, final int denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    /**
     * Constructor
     *
     * @param numerator numerator
     */
    public BigFraction(final long numerator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(1L));
    }

    /**
     * Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws IllegalArgumentException when {@code denominator == 0}
     */
    public BigFraction(final long numerator, final long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));

    }

    /**
     * Constructor
     *
     * @param numerator numerator
     * @throws NullPointerException when {@code numerator == null}
     * @throws IllegalArgumentException when {@code denominator == 0}
     */
    public BigFraction(final @NotNull BigInteger numerator) {
        this(numerator, BigInteger.ONE);
    }

    /**
     * Constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws NullPointerException when {@code numerator == null}
     * @throws NullPointerException when {@code denominator == null}
     * @throws IllegalArgumentException when {@code denominator == 0}
     */
    public BigFraction(final @NotNull BigInteger numerator, final @NotNull BigInteger denominator) {
        super(numerator, denominator);
        checkArgument(
            denominator.compareTo(BigInteger.ZERO) != 0,
            "denominator expected not to be 0 but denominator=%s",
            denominator);
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
    public int compareTo(@Nullable final BigFraction o) {
        return NullableBigFractionComparator.INSTANCE.compare(this, o);
    }

    @Override
    protected @NotNull QuotientField<@NotNull BigInteger, @NotNull BigDecimal, @NotNull BigInteger> quotientField() {
        return BigIntegerQuotientField.INSTANCE;
    }

    @Override
    protected BigFraction one() {
        return ONE;
    }

    @Override
    protected @NotNull BiFunction<BigInteger, BigInteger, BigFraction> constructor() {
        return BigFraction::new;
    }

    /**
     * Comparator which rejects null
     *
     * @author Lars Tennstedt
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class NonNullBigFractionComparator implements Comparator<BigFraction>, Serializable {
        /**
         * Instance
         */
        public static final NonNullBigFractionComparator INSTANCE = new NonNullBigFractionComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NonNullBigFractionComparator() {
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

    /**
     * Comparator which accepts null
     *
     * @author Lars Tennstedt
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class NullableBigFractionComparator implements Comparator<BigFraction>, Serializable {
        /**
         * Instance
         */
        public static final NullableBigFractionComparator INSTANCE = new NullableBigFractionComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NullableBigFractionComparator() {
        }

        @Override
        public int compare(@Nullable final BigFraction o1, @Nullable final BigFraction o2) {
            return Comparator.nullsFirst(NonNullBigFractionComparator.INSTANCE).compare(o1, o2);
        }
    }
}

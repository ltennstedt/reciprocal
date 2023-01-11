package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reciprocal.ReciprocalUtils;

/**
 * Immutable implementation of a circle which uses {@link BigDecimal} as type for its radius
 */
public final class BigCircle extends AbstractCircle<BigDecimal, BigCircle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final int radius) {
        this(BigDecimal.valueOf(radius));
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final long radius) {
        this(BigDecimal.valueOf(radius));
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final float radius) {
        this(BigDecimal.valueOf(radius));
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final double radius) {
        this(BigDecimal.valueOf(radius));
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws NullPointerException when {@code radius == null}
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final @NotNull BigInteger radius) {
        this(new BigDecimal(radius));
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws NullPointerException when {@code radius == null}
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public BigCircle(final @NotNull BigDecimal radius) {
        super(radius);
        checkArgument(radius.compareTo(BigDecimal.ZERO) > 0, "radius > 0 expected but radius = %s", radius);
    }

    @Override
    public @NotNull BigDecimal diameter() {
        return BigDecimal.valueOf(2L).multiply(getRadius());
    }

    @Override
    public @NotNull BigDecimal circumference() {
        return ReciprocalUtils.BIG_PI.multiply(diameter());
    }

    @Override
    public @NotNull BigDecimal area() {
        return ReciprocalUtils.BIG_PI.multiply(getRadius().pow(2));
    }

    @Override
    public int compareTo(final @Nullable BigCircle o) {
        return NullableBigCircleComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator which rejects null
     *
     * @author Lars Tennstedt
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class NonNullBigCircleComparator implements Comparator<BigCircle>, Serializable {
        /**
         * Instance
         */
        public static final @NotNull NonNullBigCircleComparator INSTANCE = new NonNullBigCircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NonNullBigCircleComparator() {
        }

        @Override
        public int compare(final @NotNull BigCircle o1, final @NotNull BigCircle o2) {
            requireNonNull(o1, "o1");
            requireNonNull(o2, "o2");
            return o1.getRadius().compareTo(o2.getRadius());
        }
    }

    /**
     * Comparator which accepts null
     *
     * @author Lars Tennstedt
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class NullableBigCircleComparator implements Comparator<BigCircle>, Serializable {
        /**
         * Instance
         */
        public static final @NotNull NullableBigCircleComparator INSTANCE = new NullableBigCircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NullableBigCircleComparator() {
        }

        @Override
        public int compare(final @Nullable BigCircle o1, final @Nullable BigCircle o2) {
            return Comparator.nullsFirst(NonNullBigCircleComparator.INSTANCE).compare(o1, o2);
        }
    }
}

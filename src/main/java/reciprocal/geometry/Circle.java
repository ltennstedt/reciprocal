package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable implementation of a circle which uses {@link Double} as type for its radius
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Circle extends AbstractCircle<Double, Circle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public Circle(final int radius) {
        this((double) radius);
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public Circle(final long radius) {
        this((double) radius);
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public Circle(final float radius) {
        this((double) radius);
    }

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     */
    public Circle(final double radius) {
        super(radius);
        checkArgument(radius > 0.0D, "radius > 0 expected but radius = %s", radius);
    }

    @Override
    public @NotNull Double diameter() {
        return 2.0D * getRadius();
    }

    @Override
    public @NotNull Double circumference() {
        return Math.PI * diameter();
    }

    @Override
    public @NotNull Double area() {
        return Math.PI * Math.pow(getRadius(), 2.0D);
    }

    @Override
    public int compareTo(@Nullable final Circle o) {
        return NullableCircleComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator which rejects null
     *
     * @author Lars Tennstedt
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class NonNullCircleComparator implements Comparator<Circle>, Serializable {
        /**
         * Instance
         */
        public static final @NotNull NonNullCircleComparator INSTANCE = new NonNullCircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NonNullCircleComparator() {
        }

        @Override
        public int compare(final @NotNull Circle o1, final @NotNull Circle o2) {
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
    public static final class NullableCircleComparator implements Comparator<Circle>, Serializable {
        /**
         * Instance
         */
        public static final @NotNull NullableCircleComparator INSTANCE = new NullableCircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private NullableCircleComparator() {
        }

        @Override
        public int compare(@Nullable final Circle o1, @Nullable final Circle o2) {
            return Comparator.nullsFirst(NonNullCircleComparator.INSTANCE).compare(o1, o2);
        }
    }
}

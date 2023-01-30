package reciprocal.geometry.circle;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a circle that uses {@link Double} as type for
 * its radius
 *
 * @since 0.0.1
 */
public final class Circle extends AbstractCircle<Double, Circle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param radius radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     * @since 0.0.1
     */
    public Circle(final double radius) {
        super(radius);
        checkArgument(radius > 0.0D, "radius > 0 expected but radius = %s", radius);
    }

    @Override
    public @NotNull Double getDiameter() {
        return 2.0D * getRadius();
    }

    @Override
    public @NotNull Double getCircumference() {
        return Math.PI * getDiameter();
    }

    @Override
    public @NotNull Double getArea() {
        return Math.PI * Math.pow(getRadius(), 2.0D);
    }

    @Override
    public int compareTo(final @NotNull Circle o) {
        requireNonNull(o, "o");
        return CircleComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator
     *
     * @since 0.0.1
     */
    public static final class CircleComparator implements Comparator<@NotNull Circle>, Serializable {
        /**
         * Instance
         *
         * @since 0.0.1
         */
        public static final @NotNull CircleComparator INSTANCE = new CircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private CircleComparator() {
        }

        @Override
        public int compare(final @NotNull Circle o1, final @NotNull Circle o2) {
            requireNonNull(o1, "o1");
            requireNonNull(o2, "o2");
            return o1.getRadius().compareTo(o2.getRadius());
        }
    }
}

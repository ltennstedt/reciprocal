package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of a circle which uses {@link Double} as type for
 * its radius
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class Circle extends AbstractCircle<Double, Circle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param  radius                   radius
     * @throws IllegalArgumentException when {@code radius <= 0}
     * @since                           0.0.1
     */
    public Circle(final double radius) {
        super(radius);
        checkArgument(radius > 0.0D, "radius > 0 expected but radius = %s", radius);
    }

    @Override
    public @NonNull Double getDiameter() {
        return 2.0D * getRadius();
    }

    @Override
    public @NonNull Double getCircumference() {
        return Math.PI * getDiameter();
    }

    @Override
    public @NonNull Double getArea() {
        return Math.PI * Math.pow(getRadius(), 2.0D);
    }

    @Override
    public int compareTo(final @NonNull Circle o) {
        return CircleComparator.INSTANCE.compare(this, o);
    }

    /**
     * Comparator which rejects null
     *
     * @since 0.0.1
     */
    @API(status = Status.EXPERIMENTAL, since = "0.0.1")
    public static final class CircleComparator implements Comparator<Circle>, Serializable {
        /**
         * Instance
         *
         * @since 0.0.1
         */
        public static final @NonNull CircleComparator INSTANCE = new CircleComparator();

        @Serial
        private static final long serialVersionUID = 1L;

        private CircleComparator() {
        }

        @Override
        public int compare(final @NonNull Circle o1, final @NonNull Circle o2) {
            requireNonNull(o1, "o1");
            requireNonNull(o2, "o2");
            return o1.getRadius().compareTo(o2.getRadius());
        }
    }
}

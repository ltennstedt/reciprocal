package reciprocal.geometry;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Base class for circles
 *
 * @param <N> type of the number
 * @param <C> type of the circle
 * @since     0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractCircle<N extends Number, C extends AbstractCircle<N, C>>
        implements Comparable<C>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Radius
     *
     * @since 0.0.1
     */
    private final @NonNull N radius;

    /**
     * Constructor
     *
     * @param  radius               radius
     * @throws NullPointerException when {@code radius == null}
     * @since                       0.0.1
     */
    protected AbstractCircle(final @NonNull N radius) {
        this.radius = requireNonNull(radius, "radius");
    }

    /**
     * Diameter
     *
     * @return diameter
     * @since  0.0.1
     */
    public abstract @NonNull N getDiameter();

    /**
     * Circumference
     *
     * @return circumference
     * @since  0.0.1
     */
    public abstract @NonNull N getCircumference();

    /**
     * Area
     *
     * @return area
     * @since  0.0.1
     */
    public abstract @NonNull N getArea();

    /**
     * Radius
     *
     * @return radius
     * @since  0.0.1
     */
    public final @NonNull N getRadius() {
        return radius;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (AbstractCircle<?, ?>) obj;
        return radius.equals(other.getRadius());
    }

    @Override
    public final @NonNull String toString() {
        return getClass().getSimpleName() + "(radius=" + radius + ")";
    }
}

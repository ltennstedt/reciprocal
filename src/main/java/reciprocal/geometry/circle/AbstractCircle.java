package reciprocal.geometry.circle;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for circles
 *
 * @param <N> number
 * @param <C> circle
 * @since 0.0.1
 */
public abstract class AbstractCircle<N extends Number, C extends AbstractCircle<N, C>>
    implements Comparable<C>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final @NotNull N radius;

    /**
     * Constructor
     *
     * @param radius radius
     * @throws NullPointerException when {@code radius == null}
     * @since 0.0.1
     */
    protected AbstractCircle(final @NotNull N radius) {
        this.radius = requireNonNull(radius, "radius");
    }

    /**
     * Diameter
     *
     * @return diameter
     * @since 0.0.1
     */
    public abstract @NotNull N getDiameter();

    /**
     * Circumference
     *
     * @return circumference
     * @since 0.0.1
     */
    public abstract @NotNull N getCircumference();

    /**
     * Area
     *
     * @return area
     * @since 0.0.1
     */
    public abstract @NotNull N getArea();

    /**
     * Radius
     *
     * @return radius
     * @since 0.0.1
     */
    public final @NotNull N getRadius() {
        return radius;
    }

    @Override
    public final int hashCode() {
        return hash(radius);
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
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{radius=" + radius + "}";
    }
}

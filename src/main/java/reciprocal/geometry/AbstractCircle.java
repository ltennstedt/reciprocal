package reciprocal.geometry;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for circles
 *
 * @param <N> type of the number
 * @param <C> type of the circle
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
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
     */
    protected AbstractCircle(@NotNull final N radius) {
        this.radius = requireNonNull(radius, "radius");
    }

    /**
     * Returns the diameter
     *
     * @return diameter
     */
    public abstract @NotNull N diameter();

    /**
     * Returns the circumference
     *
     * @return circumference
     */
    public abstract @NotNull N circumference();

    /**
     * Returns the area
     *
     * @return area
     */
    public abstract @NotNull N area();

    /**
     * Radius
     *
     * @return radius
     */
    public final @NotNull N getRadius() {
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
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "(radius=" + radius + ")";
    }
}

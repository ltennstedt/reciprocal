package reciprocal.precondition;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Preconditions for circles
 *
 * @since 0.0.1
 */
public final class CirclePreconditions {
    private CirclePreconditions() {
    }

    /**
     * Checks radius
     *
     * @param <N> number
     * @param b {@link Boolean}
     * @param radius radius
     * @throws NullPointerException when {@code radius == 0}
     * @throws IllegalArgumentException when {@code b == false}
     * @since 0.0.1
     */
    public static <N extends Number> void checkRadius(final boolean b, final @NotNull N radius) {
        requireNonNull(radius, "radius");
        checkArgument(b, "radius > 0 expected but radius = %s", radius);
    }
}

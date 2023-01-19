package reciprocal.precondition;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Preconditions for rectangles
 *
 * @since 0.0.1
 */
public final class RectanglePreconditions {
    private RectanglePreconditions() {
    }

    /**
     * Checks length
     *
     * @param <N> number
     * @param b {@link Boolean}
     * @param length length
     * @throws NullPointerException when {@code length == null}
     * @throws IllegalArgumentException when {@code b == false}
     * @since 0.0.1
     */
    public static <N extends Number> void checkLength(final boolean b, final @NotNull N length) {
        requireNonNull(length, "length");
        checkArgument(b, "length > 0 expected but length = %s", length);
    }

    /**
     * Checks width
     *
     * @param <N> number
     * @param b {@link Boolean}
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws IllegalArgumentException when {@code b == false}
     * @since 0.0.1
     */
    public static <N extends Number> void checkWidth(final boolean b, final @NotNull N width) {
        requireNonNull(width, "width");
        checkArgument(b, "width > 0 expected but width = %s", width);
    }
}

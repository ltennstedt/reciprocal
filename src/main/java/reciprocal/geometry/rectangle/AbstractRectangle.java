package reciprocal.geometry.rectangle;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for rectangles
 *
 * @param <N> number
 * @param <R> rectangle
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractRectangle<N extends Number, R extends AbstractRectangle<N, R>> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Length
     *
     * @since 0.0.1
     */
    private final @NotNull N length;

    /**
     * Width
     *
     * @since 0.0.1
     */
    private final @NotNull N width;

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws NullPointerException when {@code width == null}
     * @since 0.0.1
     */
    protected AbstractRectangle(final @NotNull N length, final @NotNull N width) {
        this.length = requireNonNull(length, "length");
        this.width = requireNonNull(width, "width");
    }

    /**
     * Indicates if this is square
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isSquare();

    /**
     * Perimeter
     *
     * @return perimeter
     * @since 0.0.1
     */
    public abstract @NotNull N getPerimeter();

    /**
     * Diagonal
     *
     * @return diagonal
     * @since 0.0.1
     */
    public abstract @NotNull N getDiagonal();

    /**
     * Returns a copy with new length
     *
     * @param newLength length
     * @return copy
     * @throws NullPointerException when {@code newLength == null}
     * @throws IllegalArgumentException when {@code newLength <= 0}
     * @since 0.0.1
     */
    public abstract @NotNull R withLength(@NotNull N newLength);

    /**
     * Returns a copy with new width
     *
     * @param newWidth width
     * @return copy
     * @throws NullPointerException when {@code newWidth == null}
     * @throws IllegalArgumentException when {@code newWidth <= 0}
     * @since 0.0.1
     */
    public abstract @NotNull R withWidth(@NotNull N newWidth);

    /**
     * Length
     *
     * @return length
     * @since 0.0.1
     */
    public final @NotNull N getLength() {
        return length;
    }

    /**
     * Width
     *
     * @return width
     * @since 0.0.1
     */
    public final @NotNull N getWidth() {
        return width;
    }

    @Override
    public final int hashCode() {
        return hash(length, width);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (AbstractRectangle<?, ?>) obj;
        return length.equals(other.getLength()) && width.equals(other.getWidth());
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{length=" + length + ", width=" + width + "}";
    }
}

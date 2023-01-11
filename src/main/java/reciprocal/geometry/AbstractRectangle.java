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
 * Base class for rectangles
 *
 * @param <N> type of number
 * @param <R> type of rectangle
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractRectangle<N extends Number, R extends AbstractRectangle<N, R>> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final @NotNull N length;
    private final @NotNull N width;

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws NullPointerException when {@code width == null}
     */
    protected AbstractRectangle(final @NotNull N length, final @NotNull N width) {
        this.length = requireNonNull(length, "length");
        this.width = requireNonNull(width, "width");
    }

    /**
     * Indicates if this is square
     *
     * @return {@link Boolean}
     */
    public abstract boolean isSquare();

    /**
     * Returns the perimeter
     *
     * @return perimeter
     */
    public abstract @NotNull N perimeter();

    /**
     * Returns the diagonal
     *
     * @return diagonal
     */
    public abstract @NotNull N diagonal();

    /**
     * Length
     *
     * @return length
     */
    public final @NotNull N getLength() {
        return length;
    }

    /**
     * Width
     *
     * @return width
     */
    public final @NotNull N getWidth() {
        return width;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(length, width);
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
        return getClass().getSimpleName() + "(length=" + length + ", width=" + width + ")";
    }
}

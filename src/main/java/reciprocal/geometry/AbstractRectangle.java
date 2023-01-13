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
 * Base class for rectangles
 *
 * @param <N> type of number
 * @param <R> type of rectangle
 * @since     0.0.1
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
	private final @NonNull N length;

	/**
	 * Width
	 *
	 * @since 0.0.1
	 */
	private final @NonNull N width;

	/**
	 * Constructor
	 *
	 * @param  length               length
	 * @param  width                width
	 * @throws NullPointerException when {@code length == null}
	 * @throws NullPointerException when {@code width == null}
	 * @since                       0.0.1
	 */
	protected AbstractRectangle(final @NonNull N length, final @NonNull N width) {
		this.length = requireNonNull(length, "length");
		this.width = requireNonNull(width, "width");
	}

	/**
	 * Indicates if this is square
	 *
	 * @return {@link Boolean}
	 * @since  0.0.1
	 */
	public abstract boolean isSquare();

	/**
	 * Perimeter
	 *
	 * @return perimeter
	 * @since  0.0.1
	 */
	public abstract @NonNull N getPerimeter();

	/**
	 * Diagonal
	 *
	 * @return diagonal
	 * @since  0.0.1
	 */
	public abstract @NonNull N getDiagonal();

	/**
	 * Length
	 *
	 * @return length
	 * @since  0.0.1
	 */
	public final @NonNull N getLength() {
		return length;
	}

	/**
	 * Width
	 *
	 * @return width
	 * @since  0.0.1
	 */
	public final @NonNull N getWidth() {
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
	public final @NonNull String toString() {
		return getClass().getSimpleName() + "(length=" + length + ", width=" + width + ")";
	}
}

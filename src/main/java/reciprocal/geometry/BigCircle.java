package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import reciprocal.ReciprocalUtils;

/**
 * Immutable implementation of a circle which uses {@link BigDecimal} as type
 * for its radius
 *
 * @since 0.0.1
 */
public final class BigCircle extends AbstractCircle<BigDecimal, BigCircle> {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param  radius                   radius
	 * @throws NullPointerException     when {@code radius == null}
	 * @throws IllegalArgumentException when {@code radius <= 0}
	 * @since                           0.0.1
	 */
	public BigCircle(final @NonNull BigDecimal radius) {
		super(radius);
		checkArgument(radius.compareTo(BigDecimal.ZERO) > 0, "radius > 0 expected but radius = %s", radius);
	}

	@Override
	public @NonNull BigDecimal getDiameter() {
		return BigDecimal.valueOf(2L).multiply(getRadius());
	}

	@Override
	public @NonNull BigDecimal getCircumference() {
		return ReciprocalUtils.BIG_PI.multiply(getDiameter());
	}

	@Override
	public @NonNull BigDecimal getArea() {
		return ReciprocalUtils.BIG_PI.multiply(getRadius().pow(2));
	}

	@Override
	public int compareTo(final @NonNull BigCircle o) {
		return BigCircleComparator.INSTANCE.compare(this, o);
	}

	/**
	 * Comparator which rejects null
	 *
	 * @since 0.0.1
	 */
	@API(status = Status.EXPERIMENTAL, since = "0.0.1")
	public static final class BigCircleComparator implements Comparator<BigCircle>, Serializable {
		/**
		 * Instance
		 *
		 * @since 0.0.1
		 */
		public static final @NonNull BigCircleComparator INSTANCE = new BigCircleComparator();

		@Serial
		private static final long serialVersionUID = 1L;

		private BigCircleComparator() {
		}

		@Override
		public int compare(final @NonNull BigCircle o1, final @NonNull BigCircle o2) {
			requireNonNull(o1, "o1");
			requireNonNull(o2, "o2");
			return o1.getRadius().compareTo(o2.getRadius());
		}
	}
}

package reciprocal.linear.vector;

import com.google.common.collect.ImmutableList;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Validate;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import reciprocal.linear.field.QuotientField;

/**
 * Base class for vectors
 *
 * @param <E> element
 * @param <Q> quotient of element
 * @param <V> this
 * @param <N> norm
 * @param <A> absolute value of element
 * @since     0.0.1
 */
public abstract class AbstractVector<E extends Number, Q extends Number, V extends AbstractVector<E, Q, V, N, A>,
		N extends Number, A extends Number> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Entries
	 *
	 * @since 0.0.1
	 */
	private final @NonNull List<@NonNull VectorEntry<@NonNull E>> entries;

	/**
	 * Constructor
	 *
	 * @param  entries                  entries
	 * @throws NullPointerException     when {@code entries == null}
	 * @throws IllegalArgumentException when one element in entries is null
	 * @since                           0.0.1
	 */
	protected AbstractVector(final @NonNull List<@NonNull VectorEntry<@NonNull E>> entries) {
		Validate.noNullElements(entries, "expected all elements in entries not to be null but entries = %s", entries);
		this.entries = ImmutableList.copyOf(entries);
	}

	/**
	 * Quotient field
	 *
	 * @return quotient field
	 * @since  0.0.1
	 */
	protected abstract @NonNull QuotientField<@NonNull E, @NonNull Q, @NonNull A> getQuotientField();

	/**
	 * Entries
	 *
	 * @return entries
	 * @since  0.0.1
	 */
	public final @NonNull List<@NonNull VectorEntry<@NonNull E>> getEntries() {
		return List.copyOf(entries);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(entries);
	}

	@Override
	public final boolean equals(final @Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final var other = (AbstractVector<?, ?, ?, ?, ?>) obj;
		return entries.equals(other.getEntries());
	}

	@Override
	public final @NonNull String toString() {
		return getClass().getSimpleName() + "(entries=" + entries + ")";
	}
}

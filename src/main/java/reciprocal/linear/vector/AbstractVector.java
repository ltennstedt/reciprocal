package reciprocal.linear.vector;

import com.google.common.collect.ImmutableList;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reciprocal.linear.field.QuotientField;

/**
 * Base class for vectors
 *
 * @param <E> element
 * @param <Q> quotient of element
 * @param <V> this
 * @param <N> norm
 * @param <A> absolute value of element
 */
public abstract class AbstractVector<
    E extends Number,
    Q extends Number,
    V extends AbstractVector<E, Q, V, N, A>,
    N extends Number,
    A extends Number>
    implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries;

    /**
     * Constructor
     *
     * @param entries entries
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one element in entries is null
     */
    protected AbstractVector(final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries) {
        Validate.noNullElements(entries, "expected all elements in entries not to be null but entries = %s", entries);
        this.entries = ImmutableList.copyOf(entries);
    }

    /**
     * Quotient field
     *
     * @return quotient field
     */
    protected abstract @NotNull QuotientField<@NotNull E, @NotNull Q, @NotNull A> getQuotientField();

    /**
     * Entries
     *
     * @return entries
     */
    public final @NotNull List<@NotNull VectorEntry<@NotNull E>> getEntries() {
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
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "(entries=" + entries + ")";
    }
}

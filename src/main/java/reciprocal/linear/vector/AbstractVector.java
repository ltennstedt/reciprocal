package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
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
 * @since 0.0.1
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
    private final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries;

    /**
     * Indices
     *
     * @return indices
     * @since 0.0.1
     */
    public @NotNull List<@NotNull Integer> getIndices() {
        return entries.stream().map(VectorEntry::index).toList();
    }

    /**
     * Elements
     *
     * @return elements
     * @since 0.0.1
     */
    public @NotNull List<@NotNull E> getElements() {
        return entries.stream().map(VectorEntry::element).toList();
    }

    /**
     * Constructor
     *
     * @param entries entries
     * @throws NullPointerException when {@code entries == null}
     * @throws IllegalArgumentException when one element in entries is null
     * @throws IllegalArgumentException when {@code index < 1 || size < index} for one index
     * @since 0.0.1
     */
    protected AbstractVector(final @NotNull List<@NotNull VectorEntry<@NotNull E>> entries) {
        Validate.noNullElements(entries, "expected all elements in entries not to be null but entries = %s", entries);
        final List<Integer> expectedIndices = Stream.iterate(1, i -> i + 1).limit(getSize()).toList();
        checkArgument(getIndices().equals(expectedIndices), "expected indices == expectedIndices but %s != %s",
                getIndices(), expectedIndices);
        this.entries = List.copyOf(entries.stream().sorted().toList());
    }

    /**
     * Quotient field
     *
     * @return quotient field
     * @since 0.0.1
     */
    protected abstract @NotNull QuotientField<@NotNull E, @NotNull Q, @NotNull A> getQuotientField();

    /**
     * Size
     *
     * @return size
     * @since 0.0.1
     */
    public int getSize() {
        return entries.size();
    }

    /**
     * Entries
     *
     * @return entries
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull VectorEntry<@NotNull E>> getEntries() {
        return entries;
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

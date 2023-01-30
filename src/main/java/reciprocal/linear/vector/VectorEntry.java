package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

/**
 * Entry for vectors
 *
 * @param <E> element
 * @param index index
 * @param element element
 * @since 0.0.1
 */
public record VectorEntry<E extends Number>(int index, @NotNull E element) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param index index
     * @param element element
     * @throws IllegalArgumentException when {@code index <= 0}
     * @throws NullPointerException when {@code element == null}
     * @since 0.0.1
     */
    public VectorEntry {
        checkArgument(index > 0, "index > 0 expected but index = %s", index);
        requireNonNull(element, "element");
    }

    /**
     * Returns a copy with new index
     *
     * @param index index
     * @return copy
     * @throws IllegalArgumentException when {@code index < 1}
     * @since 0.0.1
     */
    public @NotNull VectorEntry<@NotNull E> withIndex(final int index) {
        return new VectorEntry<>(index, element);
    }

    /**
     * Returns a copy with new element
     *
     * @param element element
     * @return copy
     * @throws NullPointerException when {@code element == null}
     * @since 0.0.1
     */
    public @NotNull VectorEntry<@NotNull E> withElement(final @NotNull E element) {
        requireNonNull(element, "element");
        return new VectorEntry<>(index, element);
    }
}

package reciprocal.linear.vector;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

/**
 * Entry for vectors
 *
 * @param <E> type of element
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
        checkArgument(index > 0, "expected index > 0 but index = %s", index);
        requireNonNull(element, "element");
    }

    /**
     * Returns a copy with new index
     *
     * @param newIndex new index
     * @return copy
     * @throws IllegalArgumentException when {@code newIndex < 1}
     * @since 0.0.1
     */
    public @NotNull VectorEntry<@NotNull E> withIndex(final int newIndex) {
        checkArgument(newIndex > 0, "expected newIndex > 0 but newIndex = %s", newIndex);
        return new VectorEntry<>(newIndex, element);
    }

    /**
     * Returns a copy with new element
     *
     * @param newElement new element
     * @return copy
     * @throws NullPointerException when {@code newElement == null}
     * @since 0.0.1
     */
    public @NotNull VectorEntry<@NotNull E> withElement(final @NotNull E newElement) {
        requireNonNull(newElement, "newElement");
        return new VectorEntry<>(index, newElement);
    }
}

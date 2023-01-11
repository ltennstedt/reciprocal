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
     */
    public VectorEntry {
        checkArgument(index > 0, "expected index > 0 but index = %s", index);
        requireNonNull(element, "element");
    }
}

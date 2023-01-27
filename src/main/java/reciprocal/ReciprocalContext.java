package reciprocal;

import static java.util.Objects.requireNonNull;

import java.math.MathContext;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Context
 *
 * @param scale scale
 * @param mathContext {@link MathContext}
 * @since 0.0.1
 */
public record ReciprocalContext(int scale, @NonNull MathContext mathContext) {
    /**
     * Constructor
     *
     * @param scale scale
     * @param mathContext {@link MathContext}
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public ReciprocalContext {
        requireNonNull(mathContext, "mathContext");
    }
}

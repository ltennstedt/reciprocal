package reciprocal;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.math.MathContext;

import static java.util.Objects.requireNonNull;

/**
 * Immutable data class holding a scale and a MathContext
 *
 * @param scale       scale
 * @param mathContext {@link MathContext}
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public record ReciprocalContext(int scale, @NotNull MathContext mathContext) {
    /**
     * Constructor
     *
     * @param scale       scale
     * @param mathContext {@link MathContext}
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public ReciprocalContext {
        requireNonNull(mathContext, "mathContext");
    }
}

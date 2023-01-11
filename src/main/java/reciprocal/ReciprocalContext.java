package reciprocal;

import static java.util.Objects.requireNonNull;

import java.math.MathContext;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable data class holding a scale and a MathContext
 *
 * @param scale scale
 * @param mathContext {@link MathContext}
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public record ReciprocalContext(int scale, @NotNull MathContext mathContext) {
    /**
     * Constructor
     *
     * @param scale scale
     * @param mathContext {@link MathContext}
     * @throws NullPointerException when {@code mathContext == null}
     */
    public ReciprocalContext {
        requireNonNull(mathContext, "mathContext");
    }
}

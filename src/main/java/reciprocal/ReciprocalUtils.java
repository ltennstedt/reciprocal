package reciprocal;

import java.math.BigDecimal;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Class for global constants
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class ReciprocalUtils {
    /**
     * Pi
     */
    public static final @NotNull BigDecimal BIG_PI = BigDecimal.valueOf(Math.PI);
    /**
     * e
     */
    public static final @NotNull BigDecimal BIG_E = BigDecimal.valueOf(Math.E);

    private ReciprocalUtils() {
    }
}

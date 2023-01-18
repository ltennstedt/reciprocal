package reciprocal;

import java.math.BigDecimal;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Global constants
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class ReciprocalUtils {
    /**
     * pi
     *
     * @since 0.0.1
     */
    public static final @NotNull BigDecimal BIG_PI = BigDecimal.valueOf(Math.PI);
    /**
     * e
     *
     * @since 0.0.1
     */
    public static final @NotNull BigDecimal BIG_E = BigDecimal.valueOf(Math.E);

    private ReciprocalUtils() {
    }
}

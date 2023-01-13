package reciprocal.number.complex;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of the polar form of a complex number which uses {@link BigDecimal} as type for its
 * coordinates
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigPolarForm extends AbstractPolarForm<BigDecimal, BigPolarForm> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param radial radial
     * @param angular angular
     * @throws NullPointerException when {@code radial == null}
     * @throws NullPointerException when {@code angular == null}
     * @since 0.0.1
     */
    public BigPolarForm(final @NotNull BigDecimal radial, final @NotNull BigDecimal angular) {
        super(radial, angular);
    }

    @Override
    public boolean equalsByComparing(final @NotNull BigPolarForm other) {
        requireNonNull(other, "other");
        return getRadial().compareTo(other.getRadial()) == 0 && getAngular().compareTo(other.getAngular()) == 0;
    }
}

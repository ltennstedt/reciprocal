package reciprocal.number.complex;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Immutable implementation of the polar form of a complex number that uses
 * {@link BigDecimal} as type for its coordinates
 *
 * @since 0.0.1
 */
public final class BigPolarForm extends AbstractPolarForm<@NonNull BigDecimal, @NonNull BigPolarForm> {
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
    public BigPolarForm(final @NonNull BigDecimal radial, final @NonNull BigDecimal angular) {
        super(radial, angular);
    }

    @Override
    public boolean equalsByComparing(final @NonNull BigPolarForm other) {
        requireNonNull(other, "other");
        return getRadial().compareTo(other.getRadial()) == 0 && getAngular().compareTo(other.getAngular()) == 0;
    }
}

package reciprocal.number.complex;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of the polar form of a complex number that uses
 * {@link Double} as type for its coordinates
 *
 * @since 0.0.1
 */
public final class PolarForm extends AbstractPolarForm<@NotNull Double, @NotNull PolarForm> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param radial radial
     * @param angular angular
     * @since 0.0.1
     */
    public PolarForm(final double radial, final double angular) {
        super(radial, angular);
    }

    @Override
    public boolean equalsByComparing(final @NotNull PolarForm other) {
        requireNonNull(other, "other");
        return getRadial().compareTo(other.getRadial()) == 0 && getAngular().compareTo(other.getAngular()) == 0;
    }
}

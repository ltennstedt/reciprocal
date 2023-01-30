package reciprocal.number.complex;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Base class for polar forms
 *
 * @param <N> number
 * @param <T> this
 * @since 0.0.1
 */
public abstract class AbstractPolarForm<N extends Number, T extends AbstractPolarForm<N, T>> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final @NonNull N radial;
    private final @NonNull N angular;

    /**
     * Constructor
     *
     * @param radial radial
     * @param angular angular
     * @throws NullPointerException when {@code radial == null}
     * @throws NullPointerException when {@code angular == null}
     * @since 0.0.1
     */
    protected AbstractPolarForm(final @NonNull N radial, final @NonNull N angular) {
        this.radial = requireNonNull(radial, "radial");
        this.angular = requireNonNull(angular, "angular");
    }

    /**
     * Radial
     *
     * @return radial
     * @since 0.0.1
     */
    public final @NonNull N getRadial() {
        return radial;
    }

    /**
     * Angular
     *
     * @return angular
     * @since 0.0.1
     */
    public final @NonNull N getAngular() {
        return angular;
    }

    /**
     * Returns if this is equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public abstract boolean equalsByComparing(@NonNull T other);

    /**
     * Returns if this is not equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     * @since 0.0.1
     */
    public final boolean doesNotEqualByComparing(final @NonNull T other) {
        return !equalsByComparing(other);
    }

    @Override
    public final int hashCode() {
        return hash(radial, angular);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (AbstractPolarForm<?, ?>) obj;
        return radial.equals(other.getRadial()) && angular.equals(other.getAngular());
    }

    @Override
    public final @NonNull String toString() {
        return getClass().getSimpleName() + "{radial=" + radial + ", angular=" + angular + "}";
    }
}

package reciprocal.number.complex;

import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for polar forms
 *
 * @param <N> type of number
 * @param <T> type of this
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractPolarForm<N extends Number, T extends AbstractPolarForm<N, T>>
    implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final @NotNull N radial;
    private final @NotNull N angular;

    /**
     * Constructor
     *
     * @param radial radial
     * @param angular angular
     * @throws NullPointerException when {@code radial == null}
     * @throws NullPointerException when {@code angular == null}
     */
    protected AbstractPolarForm(final @NotNull N radial, final @NotNull N angular) {
        this.radial = requireNonNull(radial, "radial");
        this.angular = requireNonNull(angular, "angular");
    }

    /**
     * Radial
     *
     * @return radial
     */
    public final @NotNull N getRadial() {
        return radial;
    }

    /**
     * Angular
     *
     * @return angular
     */
    public final @NotNull N getAngular() {
        return angular;
    }

    /**
     * Returns if this is equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public abstract boolean equalsByComparing(@NotNull T other);

    /**
     * Returns if this is not equal by comparing to other
     *
     * @param other other
     * @return {@link Boolean}
     * @throws NullPointerException when {@code other == null}
     */
    public final boolean doesNotEqualByComparing(final @NotNull T other) {
        requireNonNull(other, "other");
        return !equalsByComparing(other);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(radial, angular);
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
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "(radial=" + radial + ", angular=" + angular + ")";
    }
}

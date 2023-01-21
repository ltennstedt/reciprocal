package reciprocal.polynomial;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.hash;
import static org.apache.commons.lang3.Validate.noNullElements;

import java.util.Collections;
import java.util.List;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Base class for polynomials
 *
 * @param <C> coefficient
 * @param <P> polynomial
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractPolynomial<C extends Number, P extends AbstractPolynomial<C, P>> {
    private final @NonNull List<@NonNull C> coefficients;

    /**
     * Constructor
     *
     * @param coefficients coefficients
     * @throws NullPointerException when {@code coefficients == null}
     * @throws IllegalArgumentException when {@code coefficient == null} for one coefficient in coefficients
     * @since 0.0.1
     */
    protected AbstractPolynomial(final @NonNull List<@NonNull C> coefficients) {
        this.coefficients = noNullElements(List.copyOf(coefficients));
    }

    /**
     * Degree
     *
     * @return degree
     * @since 0.0.1
     */
    public final int getDegree() {
        return coefficients.size();
    }

    /**
     * Indicates if {@code this} is zero
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public abstract boolean isZero();

    /**
     * Indicates if {@code this} is constant
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isConstant() {
        return getDegree() == 0;
    }

    /**
     * Indicates if {@code this} is linear
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isLinear() {
        return getDegree() == 1;
    }

    /**
     * Indicates if {@code this} is quadratic
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isQuadratic() {
        return getDegree() == 2;
    }

    /**
     * Indicates if {@code this} is cubic
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isCubic() {
        return getDegree() == 3;
    }

    /**
     * Indicates if {@code this} is quartic
     *
     * @return {@link Boolean}
     * @since 0.0.1
     */
    public final boolean isQuartic() {
        return getDegree() == 4;
    }

    /**
     * Derivative
     *
     * @return derivative
     * @since 0.0.1
     */
    public abstract @NonNull List<@NonNull C> getDerivative();

    /**
     * Returns the coefficient of a given index
     *
     * @param index index
     * @return coefficient
     * @throws IllegalArgumentException when {@code index < 0}
     * @since 0.0.1
     */
    public final @NonNull C get(final int index) {
        checkArgument(index > -1, "expected index > -1 but index = %s", index);
        return coefficients.get(index);
    }

    /**
     * Coefficients
     *
     * @return coefficients
     * @since 0.0.1
     */
    public final @NonNull List<@NonNull C> getCoefficients() {
        return Collections.unmodifiableList(coefficients);
    }

    @Override
    public final int hashCode() {
        return hash(coefficients);
    }

    @Override
    public final boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var that = (AbstractPolynomial<?, ?>) obj;
        return coefficients.equals(that.getCoefficients());
    }

    @Override
    public final @NonNull String toString() {
        return getClass().getSimpleName() + "{coefficients=" + coefficients + "}";
    }
}

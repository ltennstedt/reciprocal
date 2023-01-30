package reciprocal.polynomial;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.noNullElements;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for polynomials
 *
 * @param <C> coefficient
 * @param <P> polynomial
 * @since 0.0.1
 */
public abstract class AbstractPolynomial<C extends Number, P extends AbstractPolynomial<C, P>> {
    private final @NotNull List<@NotNull C> coefficients;

    /**
     * Constructor
     *
     * @param coefficients coefficients
     * @throws NullPointerException when {@code coefficients == null}
     * @throws IllegalArgumentException when {@code coefficient == null} for one coefficient in coefficients
     * @since 0.0.1
     */
    protected AbstractPolynomial(final @NotNull List<@NotNull C> coefficients) {
        requireNonNull(coefficients, "coefficients");
        noNullElements(coefficients, "all coefficients expected not to be null but coefficients = %s", coefficients);
        this.coefficients = List.copyOf(coefficients);
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
    public abstract @NotNull List<@NotNull C> getDerivative();

    /**
     * Returns the coefficient of a given index
     *
     * @param index index
     * @return coefficient
     * @throws IllegalArgumentException when {@code index < 0}
     * @since 0.0.1
     */
    public final @NotNull C get(final int index) {
        checkArgument(index > -1, "expected index > -1 but index = %s", index);
        return coefficients.get(index);
    }

    /**
     * Coefficients
     *
     * @return coefficients
     * @since 0.0.1
     */
    public final @NotNull List<@NotNull C> getCoefficients() {
        return List.copyOf(coefficients);
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
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{coefficients=" + coefficients + "}";
    }
}

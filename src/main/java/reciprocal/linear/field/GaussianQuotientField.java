package reciprocal.linear.field;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import reciprocal.number.complex.Complex;
import reciprocal.number.complex.Gaussian;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Quotient field for {@link Gaussian Gaussians}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class GaussianQuotientField implements QuotientField<Gaussian, Complex, Double> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NotNull GaussianQuotientField INSTANCE = new GaussianQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private GaussianQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull Gaussian, @NotNull Gaussian, @NotNull Gaussian> getAddition() {
        return Gaussian::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull Gaussian, @NotNull Gaussian, @NotNull Gaussian> getSubtraction() {
        return Gaussian::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull Gaussian, @NotNull Gaussian, @NotNull Gaussian> getMultiplication() {
        return Gaussian::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull Gaussian, @NotNull Gaussian, @NotNull Complex> getDivision() {
        return Gaussian::divide;
    }

    @Override
    public @NotNull BiFunction<@NotNull Gaussian, @NotNull Integer, @NotNull Complex> getPower() {
        return Gaussian::pow;
    }

    @Override
    public @NotNull Function<@NotNull Gaussian, @NotNull Gaussian> getNegation() {
        return Gaussian::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull Gaussian, @NotNull Gaussian> getEqualityByComparing() {
        return Gaussian::equalsByComparing;
    }

    @Override
    public @NotNull Function<@NotNull Gaussian, @NotNull Double> getAbsOperator() {
        return Gaussian::abs;
    }

    @Override
    public @NotNull Gaussian getZero() {
        return Gaussian.ZERO;
    }

    @Override
    public @NotNull Gaussian getOne() {
        return Gaussian.ONE;
    }
}

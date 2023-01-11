package reciprocal.linear.field;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import reciprocal.number.complex.BigComplex;
import reciprocal.number.complex.BigGaussian;

/**
 * Quotient field for {@link BigGaussian BigGaussians}
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigGaussianQuotientField implements QuotientField<BigGaussian, BigComplex, BigDecimal> {
    /**
     * Instance
     */
    public static final @NotNull BigGaussianQuotientField INSTANCE = new BigGaussianQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigGaussianQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull BigGaussian, @NotNull BigGaussian, @NotNull BigGaussian> getAddition() {
        return BigGaussian::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigGaussian, @NotNull BigGaussian, @NotNull BigGaussian> getSubtraction() {
        return BigGaussian::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigGaussian, @NotNull BigGaussian, @NotNull BigGaussian> getMultiplication() {
        return BigGaussian::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigGaussian, @NotNull BigGaussian, @NotNull BigComplex> getDivision() {
        return BigGaussian::divide;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigGaussian, @NotNull Integer, @NotNull BigComplex> getPower() {
        return BigGaussian::pow;
    }

    @Override
    public @NotNull Function<@NotNull BigGaussian, @NotNull BigGaussian> getNegation() {
        return BigGaussian::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull BigGaussian, @NotNull BigGaussian> getEqualityByComparing() {
        return BigGaussian::equalsByComparing;
    }

    @Override
    public @NotNull Function<@NotNull BigGaussian, @NotNull BigDecimal> getAbsOperator() {
        return BigGaussian::abs;
    }

    @Override
    public @NotNull BigGaussian getZero() {
        return BigGaussian.ZERO;
    }

    @Override
    public @NotNull BigGaussian getOne() {
        return BigGaussian.ONE;
    }
}

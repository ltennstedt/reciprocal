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

/**
 * Quotient field for {@link BigComplex BigComplexs}
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigComplexQuotientField implements QuotientField<BigComplex, BigComplex, BigDecimal> {
    /**
     * Instance
     */
    public static final @NotNull BigComplexQuotientField INSTANCE = new BigComplexQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigComplexQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull BigComplex, @NotNull BigComplex, @NotNull BigComplex> getAddition() {
        return BigComplex::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigComplex, @NotNull BigComplex, @NotNull BigComplex> getSubtraction() {
        return BigComplex::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigComplex, @NotNull BigComplex, @NotNull BigComplex> getMultiplication() {
        return BigComplex::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigComplex, @NotNull BigComplex, @NotNull BigComplex> getDivision() {
        return BigComplex::divide;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigComplex, @NotNull Integer, @NotNull BigComplex> getPower() {
        return BigComplex::pow;
    }

    @Override
    public @NotNull Function<@NotNull BigComplex, @NotNull BigComplex> getNegation() {
        return BigComplex::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull BigComplex, @NotNull BigComplex> getEqualityByComparing() {
        return BigComplex::equalsByComparing;
    }

    @Override
    public @NotNull Function<@NotNull BigComplex, @NotNull BigDecimal> getAbsOperator() {
        return BigComplex::abs;
    }

    @Override
    public @NotNull BigComplex getZero() {
        return BigComplex.ZERO;
    }

    @Override
    public @NotNull BigComplex getOne() {
        return BigComplex.ONE;
    }
}

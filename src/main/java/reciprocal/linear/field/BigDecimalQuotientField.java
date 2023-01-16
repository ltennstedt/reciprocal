package reciprocal.linear.field;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Quotient field for {@link BigDecimal BigDecimals}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigDecimalQuotientField implements
    QuotientField<@NotNull BigDecimal, @NotNull BigDecimal, @NotNull BigDecimal> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NotNull BigDecimalQuotientField INSTANCE = new BigDecimalQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigDecimalQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull BigDecimal, @NotNull BigDecimal, @NotNull BigDecimal> getAddition() {
        return BigDecimal::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigDecimal, @NotNull BigDecimal, @NotNull BigDecimal> getSubtraction() {
        return BigDecimal::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigDecimal, @NotNull BigDecimal, @NotNull BigDecimal> getMultiplication() {
        return BigDecimal::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigDecimal, @NotNull BigDecimal, @NotNull BigDecimal> getDivision() {
        return (x, y) -> x.divide(y, MathContext.DECIMAL128);
    }

    @Override
    public @NotNull BiFunction<@NotNull BigDecimal, @NotNull Integer, @NotNull BigDecimal> getPower() {
        return BigDecimal::pow;
    }

    @Override
    public @NotNull Function<@NotNull BigDecimal, @NotNull BigDecimal> getNegation() {
        return BigDecimal::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull BigDecimal, @NotNull BigDecimal> getEqualityByComparing() {
        return (x, y) -> x.compareTo(y) == 0;
    }

    @Override
    public @NotNull Function<@NotNull BigDecimal, @NotNull BigDecimal> getAbsOperator() {
        return BigDecimal::abs;
    }

    @Override
    public @NotNull BigDecimal getZero() {
        return BigDecimal.ZERO;
    }

    @Override
    public @NotNull BigDecimal getOne() {
        return BigDecimal.ONE;
    }
}

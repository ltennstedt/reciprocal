package reciprocal.linear.field;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Quotient field for {@link BigDecimal BigDecimals}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigDecimalQuotientField implements QuotientField<BigDecimal, BigDecimal, BigDecimal> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NonNull BigDecimalQuotientField INSTANCE = new BigDecimalQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigDecimalQuotientField() {
    }

    @Override
    public @NonNull BiFunction<@NonNull BigDecimal, @NonNull BigDecimal, @NonNull BigDecimal> getAddition() {
        return BigDecimal::add;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigDecimal, @NonNull BigDecimal, @NonNull BigDecimal> getSubtraction() {
        return BigDecimal::subtract;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigDecimal, @NonNull BigDecimal, @NonNull BigDecimal> getMultiplication() {
        return BigDecimal::multiply;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigDecimal, @NonNull BigDecimal, @NonNull BigDecimal> getDivision() {
        return (x, y) -> x.divide(y, MathContext.DECIMAL128);
    }

    @Override
    public @NonNull BiFunction<@NonNull BigDecimal, @NonNull Integer, @NonNull BigDecimal> getPower() {
        return BigDecimal::pow;
    }

    @Override
    public @NonNull Function<@NonNull BigDecimal, @NonNull BigDecimal> getNegation() {
        return BigDecimal::negate;
    }

    @Override
    public @NonNull BiPredicate<@NonNull BigDecimal, @NonNull BigDecimal> getEqualityByComparing() {
        return (x, y) -> x.compareTo(y) == 0;
    }

    @Override
    public @NonNull Function<@NonNull BigDecimal, @NonNull BigDecimal> getAbsOperator() {
        return BigDecimal::abs;
    }

    @Override
    public @NonNull BigDecimal getZero() {
        return BigDecimal.ZERO;
    }

    @Override
    public @NonNull BigDecimal getOne() {
        return BigDecimal.ONE;
    }
}

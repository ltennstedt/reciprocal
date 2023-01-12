package reciprocal.linear.field;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Quotient field for {@link Double Doubles}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class DoubleQuotientField implements QuotientField<Double, Double, Double> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NotNull DoubleQuotientField INSTANCE = new DoubleQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private DoubleQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull Double, @NotNull Double, @NotNull Double> getAddition() {
        return Double::sum;
    }

    @Override
    public @NotNull BiFunction<@NotNull Double, @NotNull Double, @NotNull Double> getSubtraction() {
        return (x, y) -> x - y;
    }

    @Override
    public @NotNull BiFunction<@NotNull Double, @NotNull Double, @NotNull Double> getMultiplication() {
        return (x, y) -> x * y;
    }

    @Override
    public @NotNull BiFunction<@NotNull Double, @NotNull Double, @NotNull Double> getDivision() {
        return (x, y) -> x / y;
    }

    @Override
    public @NotNull BiFunction<@NotNull Double, @NotNull Integer, @NotNull Double> getPower() {
        return Math::pow;
    }

    @Override
    public @NotNull Function<@NotNull Double, @NotNull Double> getNegation() {
        return x -> -x;
    }

    @Override
    public @NotNull BiPredicate<@NotNull Double, @NotNull Double> getEqualityByComparing() {
        return (x, y) -> Double.compare(x, y) == 0;
    }

    @Override
    public @NotNull Function<@NotNull Double, @NotNull Double> getAbsOperator() {
        return Math::abs;
    }

    @Override
    public @NotNull Double getZero() {
        return 0.0D;
    }

    @Override
    public @NotNull Double getOne() {
        return 1.0D;
    }
}

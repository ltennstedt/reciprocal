package reciprocal.linear.field;

import com.google.common.math.LongMath;
import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Quotient field for {@link Long Longs}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class LongQuotientField implements QuotientField<@NotNull Long, @NotNull Double, @NotNull Long> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NotNull LongQuotientField INSTANCE = new LongQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private LongQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull Long, @NotNull Long, @NotNull Long> getAddition() {
        return Long::sum;
    }

    @Override
    public @NotNull BiFunction<@NotNull Long, @NotNull Long, @NotNull Long> getSubtraction() {
        return (x, y) -> x - y;
    }

    @Override
    public @NotNull BiFunction<@NotNull Long, @NotNull Long, @NotNull Long> getMultiplication() {
        return (x, y) -> x * y;
    }

    @Override
    public @NotNull BiFunction<@NotNull Long, @NotNull Long, @NotNull Double> getDivision() {
        return (x, y) -> x.doubleValue() / y.doubleValue();
    }

    @Override
    public @NotNull BiFunction<@NotNull Long, @NotNull Integer, @NotNull Double> getPower() {
        return (x, k) -> (double) LongMath.pow(x, k);
    }

    @Override
    public @NotNull Function<@NotNull Long, @NotNull Long> getNegation() {
        return x -> -x;
    }

    @Override
    public @NotNull BiPredicate<@NotNull Long, @NotNull Long> getEqualityByComparing() {
        return (x, y) -> (long) x == y;
    }

    @Override
    public @NotNull Function<@NotNull Long, @NotNull Long> getAbsOperator() {
        return Math::abs;
    }

    @Override
    public @NotNull Long getZero() {
        return 0L;
    }

    @Override
    public @NotNull Long getOne() {
        return 1L;
    }
}

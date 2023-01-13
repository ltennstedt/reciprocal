package reciprocal.linear.field;

import com.google.common.math.LongMath;
import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Quotient field for {@link Long Longs}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class LongQuotientField implements QuotientField<Long, Double, Long> {
	/**
	 * Instance
	 *
	 * @since 0.0.1
	 */
	public static final @NonNull LongQuotientField INSTANCE = new LongQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private LongQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull Long, @NonNull Long, @NonNull Long> getAddition() {
		return Long::sum;
	}

	@Override
	public @NonNull BiFunction<@NonNull Long, @NonNull Long, @NonNull Long> getSubtraction() {
		return (x, y) -> x - y;
	}

	@Override
	public @NonNull BiFunction<@NonNull Long, @NonNull Long, @NonNull Long> getMultiplication() {
		return (x, y) -> x * y;
	}

	@Override
	public @NonNull BiFunction<@NonNull Long, @NonNull Long, @NonNull Double> getDivision() {
		return (x, y) -> x.doubleValue() / y.doubleValue();
	}

	@Override
	public @NonNull BiFunction<@NonNull Long, @NonNull Integer, @NonNull Double> getPower() {
		return (x, k) -> (double) LongMath.pow(x, k);
	}

	@Override
	public @NonNull Function<@NonNull Long, @NonNull Long> getNegation() {
		return x -> -x;
	}

	@Override
	public @NonNull BiPredicate<@NonNull Long, @NonNull Long> getEqualityByComparing() {
		return (x, y) -> (long) x == y;
	}

	@Override
	public @NonNull Function<@NonNull Long, @NonNull Long> getAbsOperator() {
		return Math::abs;
	}

	@Override
	public @NonNull Long getZero() {
		return 0L;
	}

	@Override
	public @NonNull Long getOne() {
		return 1L;
	}
}

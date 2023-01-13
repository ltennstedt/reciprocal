package reciprocal.linear.field;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

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
	public static final @NonNull DoubleQuotientField INSTANCE = new DoubleQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private DoubleQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull Double, @NonNull Double, @NonNull Double> getAddition() {
		return Double::sum;
	}

	@Override
	public @NonNull BiFunction<@NonNull Double, @NonNull Double, @NonNull Double> getSubtraction() {
		return (x, y) -> x - y;
	}

	@Override
	public @NonNull BiFunction<@NonNull Double, @NonNull Double, @NonNull Double> getMultiplication() {
		return (x, y) -> x * y;
	}

	@Override
	public @NonNull BiFunction<@NonNull Double, @NonNull Double, @NonNull Double> getDivision() {
		return (x, y) -> x / y;
	}

	@Override
	public @NonNull BiFunction<@NonNull Double, @NonNull Integer, @NonNull Double> getPower() {
		return Math::pow;
	}

	@Override
	public @NonNull Function<@NonNull Double, @NonNull Double> getNegation() {
		return x -> -x;
	}

	@Override
	public @NonNull BiPredicate<@NonNull Double, @NonNull Double> getEqualityByComparing() {
		return (x, y) -> Double.compare(x, y) == 0;
	}

	@Override
	public @NonNull Function<@NonNull Double, @NonNull Double> getAbsOperator() {
		return Math::abs;
	}

	@Override
	public @NonNull Double getZero() {
		return 0.0D;
	}

	@Override
	public @NonNull Double getOne() {
		return 1.0D;
	}
}

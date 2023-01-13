package reciprocal.linear.field;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import reciprocal.number.complex.BigComplex;

/**
 * Quotient field for {@link BigComplex BigComplexs}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigComplexQuotientField implements QuotientField<BigComplex, BigComplex, BigDecimal> {
	/**
	 * Instance
	 *
	 * @since 0.0.1
	 */
	public static final @NonNull BigComplexQuotientField INSTANCE = new BigComplexQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private BigComplexQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull BigComplex, @NonNull BigComplex, @NonNull BigComplex> getAddition() {
		return BigComplex::add;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigComplex, @NonNull BigComplex, @NonNull BigComplex> getSubtraction() {
		return BigComplex::subtract;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigComplex, @NonNull BigComplex, @NonNull BigComplex> getMultiplication() {
		return BigComplex::multiply;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigComplex, @NonNull BigComplex, @NonNull BigComplex> getDivision() {
		return BigComplex::divide;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigComplex, @NonNull Integer, @NonNull BigComplex> getPower() {
		return BigComplex::pow;
	}

	@Override
	public @NonNull Function<@NonNull BigComplex, @NonNull BigComplex> getNegation() {
		return BigComplex::negate;
	}

	@Override
	public @NonNull BiPredicate<@NonNull BigComplex, @NonNull BigComplex> getEqualityByComparing() {
		return BigComplex::equalsByComparing;
	}

	@Override
	public @NonNull Function<@NonNull BigComplex, @NonNull BigDecimal> getAbsOperator() {
		return BigComplex::abs;
	}

	@Override
	public @NonNull BigComplex getZero() {
		return BigComplex.ZERO;
	}

	@Override
	public @NonNull BigComplex getOne() {
		return BigComplex.ONE;
	}
}

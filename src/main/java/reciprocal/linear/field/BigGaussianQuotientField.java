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
import reciprocal.number.complex.BigGaussian;

/**
 * Quotient field for {@link BigGaussian BigGaussians}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigGaussianQuotientField implements QuotientField<BigGaussian, BigComplex, BigDecimal> {
	/**
	 * Instance
	 *
	 * @since 0.0.1
	 */
	public static final @NonNull BigGaussianQuotientField INSTANCE = new BigGaussianQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private BigGaussianQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull BigGaussian, @NonNull BigGaussian, @NonNull BigGaussian> getAddition() {
		return BigGaussian::add;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigGaussian, @NonNull BigGaussian, @NonNull BigGaussian> getSubtraction() {
		return BigGaussian::subtract;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigGaussian, @NonNull BigGaussian, @NonNull BigGaussian> getMultiplication() {
		return BigGaussian::multiply;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigGaussian, @NonNull BigGaussian, @NonNull BigComplex> getDivision() {
		return BigGaussian::divide;
	}

	@Override
	public @NonNull BiFunction<@NonNull BigGaussian, @NonNull Integer, @NonNull BigComplex> getPower() {
		return BigGaussian::pow;
	}

	@Override
	public @NonNull Function<@NonNull BigGaussian, @NonNull BigGaussian> getNegation() {
		return BigGaussian::negate;
	}

	@Override
	public @NonNull BiPredicate<@NonNull BigGaussian, @NonNull BigGaussian> getEqualityByComparing() {
		return BigGaussian::equalsByComparing;
	}

	@Override
	public @NonNull Function<@NonNull BigGaussian, @NonNull BigDecimal> getAbsOperator() {
		return BigGaussian::abs;
	}

	@Override
	public @NonNull BigGaussian getZero() {
		return BigGaussian.ZERO;
	}

	@Override
	public @NonNull BigGaussian getOne() {
		return BigGaussian.ONE;
	}
}

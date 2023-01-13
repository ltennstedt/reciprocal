package reciprocal.linear.field;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import reciprocal.number.complex.Complex;
import reciprocal.number.complex.Gaussian;

/**
 * Quotient field for {@link Gaussian Gaussians}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class GaussianQuotientField implements QuotientField<Gaussian, Complex, Double> {
	/**
	 * Instance
	 *
	 * @since 0.0.1
	 */
	public static final @NonNull GaussianQuotientField INSTANCE = new GaussianQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private GaussianQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull Gaussian, @NonNull Gaussian, @NonNull Gaussian> getAddition() {
		return Gaussian::add;
	}

	@Override
	public @NonNull BiFunction<@NonNull Gaussian, @NonNull Gaussian, @NonNull Gaussian> getSubtraction() {
		return Gaussian::subtract;
	}

	@Override
	public @NonNull BiFunction<@NonNull Gaussian, @NonNull Gaussian, @NonNull Gaussian> getMultiplication() {
		return Gaussian::multiply;
	}

	@Override
	public @NonNull BiFunction<@NonNull Gaussian, @NonNull Gaussian, @NonNull Complex> getDivision() {
		return Gaussian::divide;
	}

	@Override
	public @NonNull BiFunction<@NonNull Gaussian, @NonNull Integer, @NonNull Complex> getPower() {
		return Gaussian::pow;
	}

	@Override
	public @NonNull Function<@NonNull Gaussian, @NonNull Gaussian> getNegation() {
		return Gaussian::negate;
	}

	@Override
	public @NonNull BiPredicate<@NonNull Gaussian, @NonNull Gaussian> getEqualityByComparing() {
		return Gaussian::equalsByComparing;
	}

	@Override
	public @NonNull Function<@NonNull Gaussian, @NonNull Double> getAbsOperator() {
		return Gaussian::abs;
	}

	@Override
	public @NonNull Gaussian getZero() {
		return Gaussian.ZERO;
	}

	@Override
	public @NonNull Gaussian getOne() {
		return Gaussian.ONE;
	}
}

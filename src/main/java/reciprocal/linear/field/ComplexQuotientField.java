package reciprocal.linear.field;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;
import reciprocal.number.complex.Complex;

/**
 * Quotient field for {@link Complex Complexs}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class ComplexQuotientField implements QuotientField<Complex, Complex, Double> {
	/**
	 * Instance
	 *
	 * @since 0.0.1
	 */
	public static final @NonNull ComplexQuotientField INSTANCE = new ComplexQuotientField();

	@Serial
	private static final long serialVersionUID = 1L;

	private ComplexQuotientField() {
	}

	@Override
	public @NonNull BiFunction<@NonNull Complex, @NonNull Complex, @NonNull Complex> getAddition() {
		return Complex::add;
	}

	@Override
	public @NonNull BiFunction<@NonNull Complex, @NonNull Complex, @NonNull Complex> getSubtraction() {
		return Complex::subtract;
	}

	@Override
	public @NonNull BiFunction<@NonNull Complex, @NonNull Complex, @NonNull Complex> getMultiplication() {
		return Complex::multiply;
	}

	@Override
	public @NonNull BiFunction<@NonNull Complex, @NonNull Complex, @NonNull Complex> getDivision() {
		return Complex::divide;
	}

	@Override
	public @NonNull BiFunction<@NonNull Complex, @NonNull Integer, @NonNull Complex> getPower() {
		return Complex::pow;
	}

	@Override
	public @NonNull Function<@NonNull Complex, @NonNull Complex> getNegation() {
		return Complex::negate;
	}

	@Override
	public @NonNull BiPredicate<@NonNull Complex, @NonNull Complex> getEqualityByComparing() {
		return Complex::equalsByComparing;
	}

	@Override
	public @NonNull Function<@NonNull Complex, @NonNull Double> getAbsOperator() {
		return Complex::abs;
	}

	@Override
	public @NonNull Complex getZero() {
		return Complex.ZERO;
	}

	@Override
	public @NonNull Complex getOne() {
		return Complex.ONE;
	}
}

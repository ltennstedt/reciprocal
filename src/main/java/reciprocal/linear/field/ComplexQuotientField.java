package reciprocal.linear.field;

import java.io.Serial;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
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
    public static final @NotNull ComplexQuotientField INSTANCE = new ComplexQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private ComplexQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull Complex, @NotNull Complex, @NotNull Complex> getAddition() {
        return Complex::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull Complex, @NotNull Complex, @NotNull Complex> getSubtraction() {
        return Complex::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull Complex, @NotNull Complex, @NotNull Complex> getMultiplication() {
        return Complex::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull Complex, @NotNull Complex, @NotNull Complex> getDivision() {
        return Complex::divide;
    }

    @Override
    public @NotNull BiFunction<@NotNull Complex, @NotNull Integer, @NotNull Complex> getPower() {
        return Complex::pow;
    }

    @Override
    public @NotNull Function<@NotNull Complex, @NotNull Complex> getNegation() {
        return Complex::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull Complex, @NotNull Complex> getEqualityByComparing() {
        return Complex::equalsByComparing;
    }

    @Override
    public @NotNull Function<@NotNull Complex, @NotNull Double> getAbsOperator() {
        return Complex::abs;
    }

    @Override
    public @NotNull Complex getZero() {
        return Complex.ZERO;
    }

    @Override
    public @NotNull Complex getOne() {
        return Complex.ONE;
    }
}

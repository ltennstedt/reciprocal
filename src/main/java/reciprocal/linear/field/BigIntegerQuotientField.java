package reciprocal.linear.field;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Quotient field for {@link BigInteger BigIntegers}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public final class BigIntegerQuotientField implements QuotientField<BigInteger, BigDecimal, BigInteger> {
    /**
     * Instance
     *
     * @since 0.0.1
     */
    public static final @NonNull BigIntegerQuotientField INSTANCE = new BigIntegerQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigIntegerQuotientField() {
    }

    @Override
    public @NonNull BiFunction<@NonNull BigInteger, @NonNull BigInteger, @NonNull BigInteger> getAddition() {
        return BigInteger::add;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigInteger, @NonNull BigInteger, @NonNull BigInteger> getSubtraction() {
        return BigInteger::subtract;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigInteger, @NonNull BigInteger, @NonNull BigInteger> getMultiplication() {
        return BigInteger::multiply;
    }

    @Override
    public @NonNull BiFunction<@NonNull BigInteger, @NonNull BigInteger, @NonNull BigDecimal> getDivision() {
        return (x, y) -> new BigDecimal(x).divide(new BigDecimal(y), MathContext.DECIMAL128);
    }

    @Override
    public @NonNull BiFunction<@NonNull BigInteger, @NonNull Integer, @NonNull BigDecimal> getPower() {
        return (x, k) -> new BigDecimal(x).pow(k);
    }

    @Override
    public @NonNull Function<@NonNull BigInteger, @NonNull BigInteger> getNegation() {
        return BigInteger::negate;
    }

    @Override
    public @NonNull BiPredicate<@NonNull BigInteger, @NonNull BigInteger> getEqualityByComparing() {
        return (x, y) -> x.compareTo(y) == 0;
    }

    @Override
    public @NonNull Function<@NonNull BigInteger, @NonNull BigInteger> getAbsOperator() {
        return BigInteger::abs;
    }

    @Override
    public @NonNull BigInteger getZero() {
        return BigInteger.ZERO;
    }

    @Override
    public @NonNull BigInteger getOne() {
        return BigInteger.ONE;
    }
}

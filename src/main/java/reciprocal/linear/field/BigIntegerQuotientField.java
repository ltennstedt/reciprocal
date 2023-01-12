package reciprocal.linear.field;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

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
    public static final @NotNull BigIntegerQuotientField INSTANCE = new BigIntegerQuotientField();

    @Serial
    private static final long serialVersionUID = 1L;

    private BigIntegerQuotientField() {
    }

    @Override
    public @NotNull BiFunction<@NotNull BigInteger, @NotNull BigInteger, @NotNull BigInteger> getAddition() {
        return BigInteger::add;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigInteger, @NotNull BigInteger, @NotNull BigInteger> getSubtraction() {
        return BigInteger::subtract;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigInteger, @NotNull BigInteger, @NotNull BigInteger> getMultiplication() {
        return BigInteger::multiply;
    }

    @Override
    public @NotNull BiFunction<@NotNull BigInteger, @NotNull BigInteger, @NotNull BigDecimal> getDivision() {
        return (x, y) -> new BigDecimal(x).divide(new BigDecimal(y), MathContext.DECIMAL128);
    }

    @Override
    public @NotNull BiFunction<@NotNull BigInteger, @NotNull Integer, @NotNull BigDecimal> getPower() {
        return (x, k) -> new BigDecimal(x).pow(k);
    }

    @Override
    public @NotNull Function<@NotNull BigInteger, @NotNull BigInteger> getNegation() {
        return BigInteger::negate;
    }

    @Override
    public @NotNull BiPredicate<@NotNull BigInteger, @NotNull BigInteger> getEqualityByComparing() {
        return (x, y) -> x.compareTo(y) == 0;
    }

    @Override
    public @NotNull Function<@NotNull BigInteger, @NotNull BigInteger> getAbsOperator() {
        return BigInteger::abs;
    }

    @Override
    public @NotNull BigInteger getZero() {
        return BigInteger.ZERO;
    }

    @Override
    public @NotNull BigInteger getOne() {
        return BigInteger.ONE;
    }
}

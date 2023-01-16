package reciprocal.linear.field;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for quotient fields
 *
 * @param <E> type of elements
 * @param <Q> type of quotient
 * @param <A> type of absolut value
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public interface QuotientField<E extends Number, Q extends Number, A extends Number> extends Serializable {
    /**
     * Addition
     *
     * @return addition
     * @since 0.0.1
     */
    @NotNull BiFunction<@NotNull E, @NotNull E, @NotNull E> getAddition();

    /**
     * Subtraction
     *
     * @return subtraction
     * @since 0.0.1
     */
    @NotNull BiFunction<@NotNull E, @NotNull E, @NotNull E> getSubtraction();

    /**
     * Multiplication
     *
     * @return multiplication
     * @since 0.0.1
     */
    @NotNull BiFunction<@NotNull E, @NotNull E, @NotNull E> getMultiplication();

    /**
     * Division
     *
     * @return division
     * @since 0.0.1
     */
    @NotNull BiFunction<@NotNull E, @NotNull E, @NotNull Q> getDivision();

    /**
     * Power
     *
     * @return power
     * @since 0.0.1
     */
    @NotNull BiFunction<@NotNull E, @NotNull Integer, @NotNull Q> getPower();

    /**
     * Negation
     *
     * @return negation
     * @since 0.0.1
     */
    @NotNull Function<@NotNull E, @NotNull E> getNegation();

    /**
     * Equality by comparing
     *
     * @return equality by comparing
     * @since 0.0.1
     */
    @NotNull BiPredicate<@NotNull E, @NotNull E> getEqualityByComparing();

    /**
     * Absolute value operator
     *
     * @return absolute value operator
     * @since 0.0.1
     */
    @NotNull Function<@NotNull E, @NotNull A> getAbsOperator();

    /**
     * 0
     *
     * @return 0
     * @since 0.0.1
     */
    @NotNull E getZero();

    /**
     * 1
     *
     * @return 1
     * @since 0.0.1
     */
    @NotNull E getOne();
}

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
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public interface QuotientField<E extends Number, Q extends Number, A extends Number> extends Serializable {
    /**
     * Addition
     *
     * @return addition
     */
    @NotNull
    BiFunction<@NotNull E, @NotNull E, @NotNull E> getAddition();

    /**
     * Subtraction
     *
     * @return subtraction
     */
    @NotNull
    BiFunction<@NotNull E, @NotNull E, @NotNull E> getSubtraction();

    /**
     * Multiplication
     *
     * @return multiplication
     */
    @NotNull
    BiFunction<@NotNull E, @NotNull E, @NotNull E> getMultiplication();

    /**
     * Division
     *
     * @return division
     */
    @NotNull
    BiFunction<@NotNull E, @NotNull E, @NotNull Q> getDivision();

    /**
     * Power
     *
     * @return power
     */
    @NotNull
    BiFunction<@NotNull E, @NotNull Integer, @NotNull Q> getPower();

    /**
     * Negation
     *
     * @return negation
     */
    @NotNull
    Function<@NotNull E, @NotNull E> getNegation();

    /**
     * Equality by comparing
     *
     * @return equality by comparing
     */
    @NotNull
    BiPredicate<@NotNull E, @NotNull E> getEqualityByComparing();

    /**
     * Absolute value operator
     *
     * @return absolute value operator
     */
    @NotNull
    Function<@NotNull E, @NotNull A> getAbsOperator();

    /**
     * 0
     *
     * @return 0
     */
    @NotNull
    E getZero();

    /**
     * 1
     *
     * @return 1
     */
    @NotNull
    E getOne();
}

package reciprocal.linear.field;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Interface for quotient fields
 *
 * @param <E> type of elements
 * @param <Q> type of quotient
 * @param <A> type of absolut value
 * @since     0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public interface QuotientField<E extends Number, Q extends Number, A extends Number> extends Serializable {
    /**
     * Addition
     *
     * @return addition
     * @since  0.0.1
     */
    @NonNull
    BiFunction<@NonNull E, @NonNull E, @NonNull E> getAddition();

    /**
     * Subtraction
     *
     * @return subtraction
     * @since  0.0.1
     */
    @NonNull
    BiFunction<@NonNull E, @NonNull E, @NonNull E> getSubtraction();

    /**
     * Multiplication
     *
     * @return multiplication
     * @since  0.0.1
     */
    @NonNull
    BiFunction<@NonNull E, @NonNull E, @NonNull E> getMultiplication();

    /**
     * Division
     *
     * @return division
     * @since  0.0.1
     */
    @NonNull
    BiFunction<@NonNull E, @NonNull E, @NonNull Q> getDivision();

    /**
     * Power
     *
     * @return power
     * @since  0.0.1
     */
    @NonNull
    BiFunction<@NonNull E, @NonNull Integer, @NonNull Q> getPower();

    /**
     * Negation
     *
     * @return negation
     * @since  0.0.1
     */
    @NonNull
    Function<@NonNull E, @NonNull E> getNegation();

    /**
     * Equality by comparing
     *
     * @return equality by comparing
     * @since  0.0.1
     */
    @NonNull
    BiPredicate<@NonNull E, @NonNull E> getEqualityByComparing();

    /**
     * Absolute value operator
     *
     * @return absolute value operator
     * @since  0.0.1
     */
    @NonNull
    Function<@NonNull E, @NonNull A> getAbsOperator();

    /**
     * 0
     *
     * @return 0
     * @since  0.0.1
     */
    @NonNull
    E getZero();

    /**
     * 1
     *
     * @return 1
     * @since  0.0.1
     */
    @NonNull
    E getOne();
}

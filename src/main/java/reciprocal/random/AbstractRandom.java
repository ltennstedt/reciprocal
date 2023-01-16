package reciprocal.random;

import static java.util.Objects.requireNonNull;

import java.util.Random;
import java.util.stream.Stream;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for pseudorandom number generators
 *
 * @param <N> type of number
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
public abstract class AbstractRandom<N extends Number> {
    /**
     * {@link Random}
     *
     * @since 0.0.1
     */
    private final @NotNull Random random;

    /**
     * Constructor
     *
     * @since 0.0.1
     */
    protected AbstractRandom() {
        random = new Random();
    }

    /**
     * Constructor
     *
     * @param seed seed
     * @since 0.0.1
     */
    protected AbstractRandom(final long seed) {
        random = new Random(seed);
    }

    /**
     * Constructor
     *
     * @param random {@link Random}
     * @throws NullPointerException if {@code random == null}
     * @since 0.0.1
     */
    protected AbstractRandom(final @NotNull Random random) {
        this.random = requireNonNull(random, "random");
    }

    /**
     * Returns the next number
     *
     * @return next number
     * @since 0.0.1
     */
    public abstract @NotNull N next();

    /**
     * Returns the next number
     *
     * @param bound bound
     * @return next number
     * @throws IllegalArgumentException if {@code bound < 1}
     * @since 0.0.1
     */
    public abstract @NotNull N next(long bound);

    /**
     * Returns the next number
     *
     * @param origin origin
     * @param bound bound
     * @return next number
     * @throws IllegalArgumentException if {@code origin >= bound}
     * @since 0.0.1
     */
    public abstract @NotNull N next(long origin, long bound);

    /**
     * Returns a {@link Stream} of pseudorandom numbers
     *
     * @return {@link Stream} of pseudorandom numbers
     * @since 0.0.1
     */
    public abstract @NotNull Stream<@NotNull N> numbers();

    /**
     * Returns a {@link Stream} of pseudorandom numbers
     *
     * @param limit limit
     * @return {@link Stream} of pseudorandom numbers
     * @throws IllegalArgumentException if {@code limit < 0}
     * @since 0.0.1
     */
    public abstract @NotNull Stream<@NotNull N> numbers(long limit);

    /**
     * Returns a {@link Stream} of pseudorandom numbers
     *
     * @param origin origin
     * @param bound bound
     * @return {@link Stream} of pseudorandom numbers
     * @throws IllegalArgumentException if {@code origin >= bound}
     * @since 0.0.1
     */
    public abstract @NotNull Stream<@NotNull N> numbers(long origin, long bound);

    /**
     * Returns a {@link Stream} of pseudorandom numbers
     *
     * @param limit limit
     * @param origin origin
     * @param bound bound
     * @return {@link Stream} of pseudorandom numbers
     * @throws IllegalArgumentException if {@code limit < 0}
     * @throws IllegalArgumentException if {@code origin >= bound}
     * @since 0.0.1
     */
    public abstract @NotNull Stream<@NotNull N> numbers(long limit, long origin, long bound);

    /**
     * {@link Random}
     *
     * @return {@link Random}
     * @since 0.0.1
     */
    protected final @NotNull Random getRandom() {
        return random;
    }

    @Override
    public final @NotNull String toString() {
        return "AbstractRandom{random=random}";
    }
}

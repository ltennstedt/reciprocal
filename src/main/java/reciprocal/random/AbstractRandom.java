package reciprocal.random;

import static java.util.Objects.requireNonNull;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for pseudorandom number generators
 *
 * @param <N> {@link Number}
 * @since 0.0.1
 */
public abstract class AbstractRandom<N extends Number> {
    /**
     * {@link SecureRandom}
     *
     * @since 0.0.1
     */
    private final @NotNull SecureRandom secureRandom;

    /**
     * Constructor
     *
     * @since 0.0.1
     */
    protected AbstractRandom() {
        secureRandom = new SecureRandom();
    }

    /**
     * Constructor
     *
     * @param seed seed
     * @throws NullPointerException when {@code seed == null}
     * @since 0.0.1
     */
    protected AbstractRandom(final byte[] seed) {
        requireNonNull(seed, "seed");
        secureRandom = new SecureRandom(Arrays.copyOf(seed, seed.length));
    }

    /**
     * Constructor
     *
     * @param secureRandom {@link SecureRandom}
     * @throws NullPointerException if {@code secureRandom == null}
     * @since 0.0.1
     */
    protected AbstractRandom(final @NotNull SecureRandom secureRandom) {
        this.secureRandom = requireNonNull(secureRandom, "secureRandom");
    }

    /**
     * Returns the next pseudorandom number
     *
     * @return next number
     * @since 0.0.1
     */
    public abstract @NotNull N next();

    /**
     * Returns the next pseudorandom number
     *
     * @param bound bound
     * @return next number
     * @throws IllegalArgumentException if {@code bound < 1}
     * @since 0.0.1
     */
    public abstract @NotNull N next(long bound);

    /**
     * Returns the next pseudorandom number
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
     * {@link SecureRandom}
     *
     * @return {@link SecureRandom}
     * @since 0.0.1
     */
    protected final @NotNull SecureRandom getSecureRandom() {
        return secureRandom;
    }

    @Override
    public final @NotNull String toString() {
        return getClass().getSimpleName() + "{secureRandom=" + secureRandom + "}";
    }
}

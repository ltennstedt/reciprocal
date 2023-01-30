package reciprocal.random;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * Pseudorandom number generator for {@link BigInteger BigIntegers}
 *
 * @since 0.0.1
 */
public final class BigIntegerRandom extends AbstractRandom<@NotNull BigInteger> {
    /**
     * Constructor
     *
     * @since 0.0.1
     */
    public BigIntegerRandom() {
    }

    /**
     * Constructor
     *
     * @param seed seed
     * @throws NullPointerException when {@code seed == null}
     * @since 0.0.1
     */
    public BigIntegerRandom(final byte[] seed) {
        super(seed);
    }

    /**
     * Constructor
     *
     * @param secureRandom {@link SecureRandom}
     * @throws NullPointerException when {@code secureRandom == null}
     * @since 0.0.1
     */
    BigIntegerRandom(final @NotNull SecureRandom secureRandom) {
        super(secureRandom);
    }

    @Override
    public @NotNull BigInteger next() {
        return BigInteger.valueOf(getSecureRandom().nextLong());
    }

    @Override
    public @NotNull BigInteger next(final long bound) {
        checkArgument(bound > 0, "bound > 0 expected but bound = %s", bound);
        return BigInteger.valueOf(getSecureRandom().nextLong(bound));
    }

    @Override
    public @NotNull BigInteger next(final long origin, final long bound) {
        checkArgument(bound > 0, "origin < bound expected but %s >= %s", origin, bound);
        return BigInteger.valueOf(getSecureRandom().nextLong(origin, bound));
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers() {
        return getSecureRandom().longs().boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long limit) {
        checkArgument(limit > -1, "limit > -1 expected but limit = %s", limit);
        return getSecureRandom().longs(limit).boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long origin, final long bound) {
        checkArgument(bound > 0, "origin < bound expected but %s >= %s", origin, bound);
        return getSecureRandom().longs(origin, bound).boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long limit, final long origin, final long bound) {
        checkArgument(limit > -1, "limit > -1 expected but limit = %s", limit);
        checkArgument(bound > 0, "origin < bound expected but %s >= %s", origin, bound);
        return getSecureRandom().longs(limit, origin, bound).boxed().map(BigInteger::valueOf);
    }
}

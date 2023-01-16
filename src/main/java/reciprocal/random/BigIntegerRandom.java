package reciprocal.random;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.Stream;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * Pseudorandom number generator for {@link BigInteger BigIntegers}
 *
 * @since 0.0.1
 */
@API(status = Status.EXPERIMENTAL, since = "0.0.1")
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
     * @since 0.0.1
     */
    public BigIntegerRandom(final int seed) {
        super(seed);
    }

    /**
     * Constructor
     *
     * @param random {@link Random}
     * @since 0.0.1
     */
    BigIntegerRandom(final @NotNull Random random) {
        super(random);
    }

    @Override
    public @NotNull BigInteger next() {
        return BigInteger.valueOf(getRandom().nextLong());
    }

    @Override
    public @NotNull BigInteger next(final long bound) {
        checkArgument(bound > 0, "expected bound > 0 but bound = %s", bound);
        return BigInteger.valueOf(getRandom().nextLong(bound));
    }

    @Override
    public @NotNull BigInteger next(final long origin, final long bound) {
        checkArgument(bound > 0, "expected origin < bound but %s >= %s", origin, bound);
        return BigInteger.valueOf(getRandom().nextLong(origin, bound));
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers() {
        return getRandom().longs().boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long limit) {
        checkArgument(limit > -1, "expected limit > -1 but limit = %s", limit);
        return getRandom().longs(limit).boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long origin, final long bound) {
        checkArgument(bound > 0, "expected origin < bound but %s >= %s", origin, bound);
        return getRandom().longs(origin, bound).boxed().map(BigInteger::valueOf);
    }

    @Override
    public @NotNull Stream<@NotNull BigInteger> numbers(final long limit, final long origin, final long bound) {
        checkArgument(limit > -1, "expected limit > -1 but limit = %s", limit);
        checkArgument(bound > 0, "expected origin < bound but %s >= %s", origin, bound);
        return getRandom().longs(limit, origin, bound).boxed().map(BigInteger::valueOf);
    }
}

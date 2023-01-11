package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a rectangle which uses {@link Double} as type for its length and width
 */
public final class BigRectangle extends AbstractRectangle<BigDecimal, BigRectangle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final int length, final int width) {
        this(BigDecimal.valueOf(length), BigDecimal.valueOf(width));
    }

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final long length, final long width) {
        this(BigDecimal.valueOf(length), BigDecimal.valueOf(width));
    }

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final float length, final float width) {
        this(BigDecimal.valueOf(length), BigDecimal.valueOf(width));
    }

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final double length, final double width) {
        this(BigDecimal.valueOf(length), BigDecimal.valueOf(width));
    }

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws NullPointerException when {@code width == null}
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final @NotNull BigInteger length, final @NotNull BigInteger width) {
        this(new BigDecimal(length), new BigDecimal(width));
    }

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws NullPointerException when {@code width == null}
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     */
    public BigRectangle(final @NotNull BigDecimal length, final @NotNull BigDecimal width) {
        super(length, width);
        checkArgument(
            length.compareTo(BigDecimal.ZERO) > 0,
            "length expected to be greater than 0 but length = %s",
            length);
        checkArgument(width.compareTo(BigDecimal.ZERO) > 0, "width expected to be greater than 0 but width = %s",
            width);
    }

    @Override
    public boolean isSquare() {
        return getLength().compareTo(getWidth()) == 0;
    }

    @Override
    public @NotNull BigDecimal perimeter() {
        return BigDecimal.valueOf(2L).multiply(getLength().add(getWidth()));
    }

    @Override
    public @NotNull BigDecimal diagonal() {
        return getLength().pow(2).add(getWidth().pow(2)).sqrt(MathContext.DECIMAL128);
    }
}

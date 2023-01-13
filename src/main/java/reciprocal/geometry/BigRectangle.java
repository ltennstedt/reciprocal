package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a rectangle which uses {@link Double} as type for its length and width
 *
 * @since 0.0.1
 */
public final class BigRectangle extends AbstractRectangle<BigDecimal, BigRectangle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws NullPointerException when {@code length == null}
     * @throws NullPointerException when {@code width == null}
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     * @since 0.0.1
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
    public @NotNull BigDecimal getPerimeter() {
        return BigDecimal.valueOf(2L).multiply(getLength().add(getWidth()));
    }

    @Override
    public @NotNull BigDecimal getDiagonal() {
        return getLength().pow(2).add(getWidth().pow(2)).sqrt(MathContext.DECIMAL128);
    }
}

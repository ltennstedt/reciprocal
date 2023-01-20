package reciprocal.geometry.rectangle;

import static java.util.Objects.requireNonNull;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewLength;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkNewWidth;
import static reciprocal.geometry.rectangle.RectanglePreconditions.checkWidth;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a rectangle which uses {@link Double} as type for
 * its length and width
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
        checkLength(length.compareTo(BigDecimal.ZERO) > 0, length);
        checkWidth(width.compareTo(BigDecimal.ZERO) > 0, width);
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

    /**
     * Returns diagonal
     *
     * @param mathContext {@link MathContext}
     * @return diagonal
     * @throws NullPointerException when {@code mathContext == null}
     * @since 0.0.1
     */
    public @NotNull BigDecimal getDiagonal(final @NotNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getLength().pow(2).add(getWidth().pow(2)).sqrt(mathContext);
    }

    @Override
    public @NotNull BigRectangle withLength(final @NotNull BigDecimal newLength) {
        requireNonNull(newLength, "newLength");
        checkNewLength(newLength.compareTo(BigDecimal.ZERO) > 0, newLength);
        return new BigRectangle(newLength, getWidth());
    }

    @Override
    public @NotNull BigRectangle withWidth(final @NotNull BigDecimal newWidth) {
        requireNonNull(newWidth, "newWidth");
        checkNewWidth(newWidth.compareTo(BigDecimal.ZERO) > 0, newWidth);
        return new BigRectangle(getLength(), newWidth);
    }
}
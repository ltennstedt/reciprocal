package reciprocal.geometry.rectangle;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import org.eclipse.jdt.annotation.NonNull;

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
    public BigRectangle(final @NonNull BigDecimal length, final @NonNull BigDecimal width) {
        super(length, width);
        checkArgument(length.compareTo(BigDecimal.ZERO) > 0, "length > 0 expected but length = %s", length);
        checkArgument(width.compareTo(BigDecimal.ZERO) > 0, "width > 0 expected but width = %s", width);
    }

    @Override
    public boolean isSquare() {
        return getLength().compareTo(getWidth()) == 0;
    }

    @Override
    public @NonNull BigDecimal getPerimeter() {
        return BigDecimal.valueOf(2L).multiply(getLength().add(getWidth()));
    }

    @Override
    public @NonNull BigDecimal getDiagonal() {
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
    public @NonNull BigDecimal getDiagonal(final @NonNull MathContext mathContext) {
        requireNonNull(mathContext, "mathContext");
        return getLength().pow(2).add(getWidth().pow(2)).sqrt(mathContext);
    }

    @Override
    public @NonNull BigRectangle withLength(final @NonNull BigDecimal newLength) {
        requireNonNull(newLength, "newLength");
        checkArgument(newLength.compareTo(BigDecimal.ZERO) > 0, "newLength > 0 expected but newLength = %s", newLength);
        return new BigRectangle(newLength, getWidth());
    }

    @Override
    public @NonNull BigRectangle withWidth(final @NonNull BigDecimal newWidth) {
        requireNonNull(newWidth, "newWidth");
        checkArgument(newWidth.compareTo(BigDecimal.ZERO) > 0, "newWidth > 0 expected but newWidth = %s", newWidth);
        return new BigRectangle(getLength(), newWidth);
    }
}

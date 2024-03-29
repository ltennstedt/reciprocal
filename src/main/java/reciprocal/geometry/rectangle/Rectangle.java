package reciprocal.geometry.rectangle;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a rectangle which uses {@link Double} as type for
 * its length and width
 *
 * @since 0.0.1
 */
public final class Rectangle extends AbstractRectangle<Double, Rectangle> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param length length
     * @param width width
     * @throws IllegalArgumentException when {@code length <= 0}
     * @throws IllegalArgumentException when {@code width <= 0}
     * @since 0.0.1
     */
    public Rectangle(final double length, final double width) {
        super(length, width);
        checkArgument(length > 0.0D, "length > 0 expected but length = %s", length);
        checkArgument(width > 0.0D, "width > 0 expected but width = %s", width);
    }

    @Override
    public boolean isSquare() {
        return getLength().compareTo(getWidth()) == 0;
    }

    @Override
    public @NotNull Double getPerimeter() {
        return 2.0D * (getLength() + getWidth());
    }

    @Override
    public @NotNull Double getDiagonal() {
        return Math.sqrt(Math.pow(getLength(), 2.0D) + Math.pow(getWidth(), 2.0D));
    }

    @Override
    public @NotNull Rectangle withLength(final @NotNull Double newLength) {
        requireNonNull(newLength, "newLength");
        checkArgument(newLength > 0.0D, "newLength > 0 expected but newLength = %s", newLength);
        return new Rectangle(newLength, getWidth());
    }

    @Override
    public @NotNull Rectangle withWidth(final @NotNull Double newWidth) {
        requireNonNull(newWidth, "newWidth");
        checkArgument(newWidth > 0.0D, "newWidth > 0 expected but newWidth = %s", newWidth);
        return new Rectangle(getLength(), newWidth);
    }
}

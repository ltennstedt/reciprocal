package reciprocal.geometry;

import static java.util.Objects.requireNonNull;
import static reciprocal.precondition.RectanglePreconditions.checkLength;
import static reciprocal.precondition.RectanglePreconditions.checkWidth;

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
        checkLength(length > 0.0D, length);
        checkWidth(width > 0.0D, width);
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
    public @NotNull Rectangle widthLength(final @NotNull Double newLength) {
        requireNonNull(newLength, "newLength");
        checkLength(newLength > 0.0D, newLength);
        return new Rectangle(newLength, getWidth());
    }

    @Override
    public @NotNull Rectangle widthWidth(final @NotNull Double newWidth) {
        requireNonNull(newWidth, "newWidth");
        checkWidth(newWidth > 0.0D, newWidth);
        return new Rectangle(getLength(), newWidth);
    }
}

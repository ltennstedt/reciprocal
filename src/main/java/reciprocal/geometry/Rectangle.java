package reciprocal.geometry;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serial;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable implementation of a rectangle which uses {@link Double} as type for its length and width
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
    public @NotNull Double perimeter() {
        return 2.0D * (getLength() + getWidth());
    }

    @Override
    public @NotNull Double diagonal() {
        return Math.sqrt(Math.pow(getLength(), 2.0D) + Math.pow(getWidth(), 2.0D));
    }
}

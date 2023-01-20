package reciprocal.geometry.rectangle;

import static com.google.common.base.Preconditions.checkArgument;

import org.jetbrains.annotations.NotNull;

final class RectanglePreconditions {
    private RectanglePreconditions() {
    }

    static <N extends Number> void checkLength(final boolean b, final @NotNull N length) {
        checkArgument(b, "length > 0 expected but length = %s", length);
    }

    static <N extends Number> void checkWidth(final boolean b, final @NotNull N width) {
        checkArgument(b, "width > 0 expected but width = %s", width);
    }

    static <N extends Number> void checkNewLength(final boolean b, final @NotNull N newLength) {
        checkArgument(b, "newLength > 0 expected but newLength = %s", newLength);
    }

    static <N extends Number> void checkNewWidth(final boolean b, final @NotNull N newWidth) {
        checkArgument(b, "newWidth > 0 expected but newWidth = %s", newWidth);
    }
}

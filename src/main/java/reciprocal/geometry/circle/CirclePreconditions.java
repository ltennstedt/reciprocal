package reciprocal.geometry.circle;

import static com.google.common.base.Preconditions.checkArgument;

import org.jetbrains.annotations.NotNull;

final class CirclePreconditions {
    private CirclePreconditions() {
    }

    static <N extends Number> void checkRadius(final boolean b, final @NotNull N radius) {
        checkArgument(b, "radius > 0 expected but radius = %s", radius);
    }
}

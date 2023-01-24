package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.jupiter.api.Test;

final class AbstractMathContextVectorTests {
    @Test
    void taxicabDistance_should_throw_Exception_when_other_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.taxicabDistance(null, MathContext.UNLIMITED))
            .withMessage("other").withNoCause();
    }

    @Test
    void taxicabDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.taxicabDistance(other, MathContext.UNLIMITED))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void taxicabDistance_should_throw_Exception_when_mathContext_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.taxicabDistance(vector, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void taxicabDistance_should_calculate_taxicabDistance() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ONE), new VectorEntry<>(2, BigDecimal.valueOf(2L))));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(3L)), new VectorEntry<>(2, BigDecimal.valueOf(4L))));

        assertThat(vector.taxicabDistance(other, MathContext.DECIMAL32)).isEqualByComparingTo(BigDecimal.valueOf(4L));
    }

    @Test
    void euclideanDistance_should_throw_Exception_when_other_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.euclideanDistance(null, MathContext.UNLIMITED))
            .withMessage("other").withNoCause();
    }

    @Test
    void euclideanDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.euclideanDistance(other, MathContext.UNLIMITED))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void euclideanDistance_should_throw_Exception_mathContext_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.euclideanDistance(vector, null))
            .withMessage("mathContext").withNoCause();
    }

    @Test
    void euclideanDistance_should_calculate_euclideanDistance() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ONE), new VectorEntry<>(2, BigDecimal.valueOf(2L))));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(3L)), new VectorEntry<>(2, BigDecimal.valueOf(4L))));

        assertThat(vector.euclideanDistance(other, MathContext.DECIMAL32)).isEqualByComparingTo("2.828427");
    }

    @Test
    void maxDistance_should_throw_Exception_when_other_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        assertThatNullPointerException().isThrownBy(() -> vector.maxDistance(null, MathContext.UNLIMITED))
            .withMessage("other").withNoCause();
    }

    @Test
    void maxDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.maxDistance(other, MathContext.UNLIMITED))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void maxDistance_should_throw_Exception_mathContext_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.maxDistance(vector, null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void maxDistance_should_calculate_maxDistance() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ONE), new VectorEntry<>(2, BigDecimal.ONE)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(3L))));

        assertThat(vector.maxDistance(other, MathContext.DECIMAL32)).isEqualByComparingTo(BigDecimal.valueOf(2L));
    }
}

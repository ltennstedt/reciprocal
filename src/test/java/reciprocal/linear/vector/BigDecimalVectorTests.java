package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.jupiter.api.Test;

final class BigDecimalVectorTests {
    @Test
    void ofSize_should_return_Builder() {
        assertThat(BigDecimalVector.ofSize(1).getSize()).isOne();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.add(null)).withMessage("summand").withNoCause();
    }

    @Test
    void add_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.add(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void add_should_caculate_and_return_sum() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ONE)));
        final var summand = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(3L))));
        final BigDecimalVector expected = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(4L))));

        assertThat(vector.add(summand)).isEqualTo(expected);
    }

    @Test
    void add_with_MatchContext_should_throw_Exception_when_summand_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.add(null, MathContext.UNLIMITED))
            .withMessage("summand").withNoCause();
    }

    @Test
    void add_with_MathContext_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.add(other, MathContext.UNLIMITED))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void add_with_MatchContext_should_throw_Exception_when_mathContext_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.add(vector, null)).withMessage("mathContext")
            .withNoCause();
    }

    @Test
    void add_with_MathContext_should_caculate_and_return_sum() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ONE)));
        final var summand = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(3L))));
        final BigDecimalVector expected = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(4L))));

        assertThat(vector.add(summand, MathContext.DECIMAL32)).isEqualTo(expected);
    }

    @Test
    void subtract_should_throw_Exception_when_summand_is_null() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));

        assertThatNullPointerException().isThrownBy(() -> vector.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new BigDecimalVector(1, List.of(new VectorEntry<>(1, BigDecimal.ZERO)));
        final var other = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ZERO)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.subtract(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void subtract_should_caculate_and_return_sum() {
        final var vector = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.ZERO), new VectorEntry<>(2, BigDecimal.ONE)));
        final var subtrahend = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(2L)), new VectorEntry<>(2, BigDecimal.valueOf(3L))));
        final BigDecimalVector expected = new BigDecimalVector(2,
            List.of(new VectorEntry<>(1, BigDecimal.valueOf(-2L)), new VectorEntry<>(2, BigDecimal.valueOf(-2L))));

        assertThat(vector.subtract(subtrahend)).isEqualTo(expected);
    }
}

package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reciprocal.linear.vector.LongVector.LongVectorBuilder;

final class LongVectorTests {
    @Test
    void ofSize_should_return_Builder() {
        assertThat(LongVector.ofSize(1).getSize()).isOne();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThatNullPointerException().isThrownBy(() -> vector.add(null)).withMessage("summand").withNoCause();
    }

    @Test
    void add_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2,
            List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.add(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void add_should_calculate_and_return_sum() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 1L)));
        final var summand = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 3L)));
        final LongVector expected = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 4L)));

        assertThat(vector.add(summand)).isEqualTo(expected);
    }

    @Test
    void subtract_should_throw_Exception_when_summand_is_null() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThatNullPointerException().isThrownBy(() -> vector.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.subtract(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void subtract_should_calculate_and_return_difference() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 1L)));
        final var subtrahend = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 3L)));
        final LongVector expected = new LongVector(2, List.of(new VectorEntry<>(1, -2L), new VectorEntry<>(2, -2L)));

        assertThat(vector.subtract(subtrahend)).isEqualTo(expected);
    }

    @Test
    void dotProduct_should_throw_Exception_when_other_is_null() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThatNullPointerException().isThrownBy(() -> vector.dotProduct(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void dotProduct_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.dotProduct(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void dotProduct_should_calculate_and_return_dot_product() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 1L)));
        final var subtrahend = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 3L)));

        assertThat(vector.dotProduct(subtrahend)).isEqualByComparingTo(3L);
    }

    @Test
    void scalarMultiply_should_throw_Exception_when_scalar_is_null() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThatNullPointerException().isThrownBy(() -> vector.scalarMultiply(null)).withMessage("scalar")
            .withNoCause();
    }

    @Test
    void scalarMultiply_should_calculate_and_return_scalar_product() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 2L)));
        final LongVector expected = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 4L)));

        assertThat(vector.scalarMultiply(2L)).isEqualTo(expected);
    }

    @Test
    void negate_should_return_negated() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 2L)));
        final var expected = new LongVector(2, List.of(new VectorEntry<>(1, -1L), new VectorEntry<>(2, -2L)));

        assertThat(vector.negate()).isEqualTo(expected);
    }

    @Test
    void orthogonalTo_should_throw_Exception_when_other_is_null() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThatNullPointerException().isThrownBy(() -> vector.orthogonalTo(null)).withMessage("other").withNoCause();
    }

    @Test
    void orthogonalTo_should_return_false_when_vector_is_not_orthogonal_to_other() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 2L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 3L), new VectorEntry<>(2, 4L)));

        assertThat(vector.orthogonalTo(other)).isFalse();
    }

    @Test
    void orthogonalTo_should_return_true_when_vector_is_orthogonal_to_other() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 1L)));

        assertThat(vector.orthogonalTo(other)).isTrue();
    }

    @Test
    void taxicabNorm_should_calculate_and_return_taxicab_norm() {
        final var vector = new LongVector(2,
            List.of(new VectorEntry<>(1, -1L), new VectorEntry<>(2, -2L)));

        assertThat(vector.taxicabNorm()).isEqualByComparingTo(3.0D);
    }

    @Test
    void euclideanNorm_should_calculate_and_return_Euclidean_norm() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 2L)));

        assertThat(vector.euclideanNorm()).isEqualByComparingTo(2.0D);
    }

    @Test
    void maxNorm_should_calculate_and_return_max_norm() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, -2L)));

        assertThat(vector.maxNorm()).isEqualByComparingTo(2.0D);
    }

    @Nested
    final class LongVectorBuilderTests {
        @Test
        void constructor_should_throw_Exception_when_size_is_0() {
            assertThatIllegalArgumentException().isThrownBy(() -> new LongVectorBuilder(0))
                .withMessage("size > 0 expected but size = 0").withNoCause();
        }

        @Test
        void constructor_should_set_fields() {
            final var actual = new LongVectorBuilder(1);

            assertThat(actual.getSize()).isOne();
            assertThat(actual.getComputationOfAbsentees().apply(1)).isZero();
        }

        @Test
        void build_should_build_and_return_vector() {
            final var actual = new LongVectorBuilder(1).build();

            assertThat(actual.getSize()).isOne();
            assertThat(actual.getEntries()).containsExactly(new VectorEntry<>(1, 0L));
        }
    }
}

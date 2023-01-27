package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reciprocal.linear.vector.DoubleVector.DoubleVectorBuilder;

final class DoubleVectorTests {
    @Test
    void ofSize_should_return_Builder() {
        assertThat(DoubleVector.ofSize(1).getSize()).isOne();
    }

    @Test
    void add_should_throw_Exception_when_summand_is_null() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));

        assertThatNullPointerException().isThrownBy(() -> vector.add(null)).withMessage("summand").withNoCause();
    }

    @Test
    void add_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));
        final var other = new DoubleVector(2,
            List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 0.0D)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.add(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void add_should_calculate_and_return_sum() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 1.0D)));
        final var summand = new DoubleVector(2, List.of(new VectorEntry<>(1, 2.0D), new VectorEntry<>(2, 3.0D)));
        final DoubleVector expected =
            new DoubleVector(2, List.of(new VectorEntry<>(1, 2.0D), new VectorEntry<>(2, 4.0D)));

        assertThat(vector.add(summand)).isEqualTo(expected);
    }

    @Test
    void subtract_should_throw_Exception_when_summand_is_null() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));

        assertThatNullPointerException().isThrownBy(() -> vector.subtract(null)).withMessage("subtrahend")
            .withNoCause();
    }

    @Test
    void subtract_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));
        final var other = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 0.0D)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.subtract(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void subtract_should_calculate_and_return_difference() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 1.0D)));
        final var subtrahend = new DoubleVector(2, List.of(new VectorEntry<>(1, 2.0D), new VectorEntry<>(2, 3.0D)));
        final DoubleVector expected =
            new DoubleVector(2, List.of(new VectorEntry<>(1, -2.0D), new VectorEntry<>(2, -2.0D)));

        assertThat(vector.subtract(subtrahend)).isEqualTo(expected);
    }

    @Test
    void dotProduct_should_throw_Exception_when_other_is_null() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));

        assertThatNullPointerException().isThrownBy(() -> vector.dotProduct(null)).withMessage("other")
            .withNoCause();
    }

    @Test
    void dotProduct_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));
        final var other = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 0.0D)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.dotProduct(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void dotProduct_should_calculate_and_return_dot_product() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 1.0D)));
        final var subtrahend = new DoubleVector(2, List.of(new VectorEntry<>(1, 2.0D), new VectorEntry<>(2, 3.0D)));

        assertThat(vector.dotProduct(subtrahend)).isEqualByComparingTo(3.0D);
    }

    @Test
    void scalarMultiply_should_throw_Exception_when_scalar_is_null() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));

        assertThatNullPointerException().isThrownBy(() -> vector.scalarMultiply(null)).withMessage("scalar")
            .withNoCause();
    }

    @Test
    void scalarMultiply_should_calculate_and_return_scalar_product() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 1.0D), new VectorEntry<>(2, 2.0D)));
        final DoubleVector expected =
            new DoubleVector(2, List.of(new VectorEntry<>(1, 2.0D), new VectorEntry<>(2, 4.0D)));

        assertThat(vector.scalarMultiply(2.0D)).isEqualTo(expected);
    }

    @Test
    void negate_should_return_negated() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 1.0D), new VectorEntry<>(2, 2.0D)));
        final var expected = new DoubleVector(2, List.of(new VectorEntry<>(1, -1.0D), new VectorEntry<>(2, -2.0D)));

        assertThat(vector.negate()).isEqualTo(expected);
    }

    @Test
    void orthogonalTo_should_throw_Exception_when_other_is_null() {
        final var vector = new DoubleVector(1, List.of(new VectorEntry<>(1, 0.0D)));

        assertThatNullPointerException().isThrownBy(() -> vector.orthogonalTo(null)).withMessage("other").withNoCause();
    }

    @Test
    void orthogonalTo_should_return_false_when_vector_is_not_orthogonal_to_other() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 1.0D), new VectorEntry<>(2, 2.0D)));
        final var other = new DoubleVector(2, List.of(new VectorEntry<>(1, 3.0D), new VectorEntry<>(2, 4.0D)));

        assertThat(vector.orthogonalTo(other)).isFalse();
    }

    @Test
    void orthogonalTo_should_return_true_when_vector_is_orthogonal_to_other() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 1.0D), new VectorEntry<>(2, 0.0D)));
        final var other = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 1.0D)));

        assertThat(vector.orthogonalTo(other)).isTrue();
    }

    @Test
    void taxicabNorm_should_calculate_and_return_taxicab_norm() {
        final var vector = new DoubleVector(2,
            List.of(new VectorEntry<>(1, -1.0D), new VectorEntry<>(2, -2.0D)));

        assertThat(vector.taxicabNorm()).isEqualByComparingTo(3.0D);
    }

    @Test
    void euclideanNorm_should_calculate_and_return_Euclidean_norm() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 0.0D), new VectorEntry<>(2, 2.0D)));

        assertThat(vector.euclideanNorm()).isEqualByComparingTo(2.0D);
    }

    @Test
    void maxNorm_should_calculate_and_return_max_norm() {
        final var vector = new DoubleVector(2, List.of(new VectorEntry<>(1, 1.0D), new VectorEntry<>(2, -2.0D)));

        assertThat(vector.maxNorm()).isEqualByComparingTo(2.0D);
    }

    @Nested
    final class DoubleVectorBuilderTests {
        @Test
        void constructor_should_throw_Exception_when_size_is_0() {
            assertThatIllegalArgumentException().isThrownBy(() -> new DoubleVectorBuilder(0))
                .withMessage("size > 0 expected but size = 0").withNoCause();
        }

        @Test
        void constructor_should_set_fields() {
            final var actual = new DoubleVectorBuilder(1);

            assertThat(actual.getSize()).isOne();
            assertThat(actual.getComputationOfAbsentees().apply(1)).isZero();
        }

        @Test
        void build_should_build_and_return_vector() {
            final var actual = new DoubleVectorBuilder(1).build();

            assertThat(actual.getSize()).isOne();
            assertThat(actual.getEntries()).containsExactly(new VectorEntry<>(1, 0.0D));
        }
    }
}

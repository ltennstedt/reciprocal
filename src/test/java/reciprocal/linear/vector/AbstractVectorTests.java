package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reciprocal.linear.vector.LongVector.LongVectorBuilder;

final class AbstractVectorTests {
    @Test
    void constructor_should_throw_Exception_when_size_is_less_than_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LongVector(0, Collections.emptyList()))
                .withMessage("size > 0 expected but size = 0").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_entries_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new LongVector(1, null)).withMessage("entries").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_entries_contains_null() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LongVector(1, Collections.singletonList(null)))
                .withMessage("all entries expected not to be null but entries = [null]").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_indices_are_incomplete() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LongVector(2, List.of(new VectorEntry<>(2, 0L))))
                .withMessage("indices == (1..size) expected but indices = [2]").withNoCause();
    }

    @Test
    void constructor_should_sort_entries() {
        final var first = new VectorEntry<>(1, 0L);
        final var second = new VectorEntry<>(2, 0L);

        assertThat(new LongVector(2, List.of(second, first)).getEntries()).containsExactly(first, second);
    }

    @Test
    void getIndices_should_return_indices() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getIndices()).containsExactly(1);
    }

    @Test
    void getElements_should_return_elements() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getElements()).containsExactly(0L);
    }

    @Test
    void getSize_should_return_size() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getSize()).isOne();
    }

    @Nested
    final class AbstractVectorBuilderTests {
        @Test
        void constructor_should_throw_Exception_when_size_is_less_than_1() {
            assertThatIllegalArgumentException().isThrownBy(() -> new LongVectorBuilder(0))
                    .withMessage("size > 0 expected but size = 0").withNoCause();
        }

        @Test
        void set_should_throw_Exception_when_index_is_less_than_1() {
            assertThatIllegalArgumentException().isThrownBy(() -> new LongVectorBuilder(1).set(0, 0L))
                    .withMessage("0 < index <= size expected but index = 0").withNoCause();
        }

        @Test
        void set_should_throw_Exception_when_index_is_greater_than_size() {
            assertThatIllegalArgumentException().isThrownBy(() -> new LongVectorBuilder(1).set(2, 0L))
                    .withMessage("0 < index <= size expected but index = 2").withNoCause();
        }

        @Test
        void set_should_throw_Exception_when_index_already_exists() {
            assertThatIllegalArgumentException().isThrownBy(() -> new LongVectorBuilder(1).set(1, 0L).set(1, 0L))
                    .withMessage("index already exists").withNoCause();
        }

        @Test
        void set_should_throw_Exception_when_element_is_null() {
            assertThatNullPointerException().isThrownBy(() -> new LongVectorBuilder(1).set(1, null))
                    .withMessage("element").withNoCause();
        }

        @Test
        void set_should_add_entry_to_entries_and_return_this() {
            final var builder = new LongVectorBuilder(1);

            final var actual = builder.set(1, 0L);

            assertThat(builder.getEntries()).containsExactly(new VectorEntry<>(1, 0L));
            assertThat(actual).isSameAs(builder);
        }

        @Test
        void computationOfAbsentees_should_throw_Exception_when_newComputationOfAbsentees_is_null() {
            assertThatNullPointerException().isThrownBy(() -> new LongVectorBuilder(1).computationOfAbsentees(null))
                    .withMessage("newComputationOfAbsentees").withNoCause();
        }

        @Test
        void computationOfAbsentees_should_set_computationOfAbsentees_and_return_this() {
            final var builder = new LongVectorBuilder(1);
            final var newComputationOfAbsentees = (IntFunction<Long>) i -> 1L;

            final var actual = builder.computationOfAbsentees(newComputationOfAbsentees);

            assertThat(builder.getComputationOfAbsentees()).isEqualTo(newComputationOfAbsentees);
            assertThat(actual).isSameAs(builder);
        }

        @Test
        void computeEntries_should_return_computed_entries() {
            assertThat(new LongVectorBuilder(1).computeEntries()).containsExactly(new VectorEntry<>(1, 0L));
        }

        @Test
        void toString_should_return_toString() {
            assertThat(new LongVectorBuilder(1)).hasToString("LongVectorBuilder{size=1, entries=[]}");
        }
    }
}

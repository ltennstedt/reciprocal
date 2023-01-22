package reciprocal.linear.vector;

import static java.util.Objects.hash;
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
            .withMessage("indices == (1..2) expected but indices = [2]").withNoCause();
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

    @Test
    void taxicabDistance_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).taxicabDistance(null))
            .withMessage("other").withNoCause();
    }

    @Test
    void taxicabDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.taxicabDistance(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void taxicabDistance_should_calculate_taxicabDistance() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 2L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 3L), new VectorEntry<>(2, 4L)));

        assertThat(vector.taxicabDistance(other)).isEqualByComparingTo(4.0D);
    }

    @Test
    void euclideanDistance_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).euclideanDistance(null))
            .withMessage("other").withNoCause();
    }

    @Test
    void euclideanDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.euclideanDistance(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void euclideanDistance_should_calculate_euclideanDistance() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 2L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 3L), new VectorEntry<>(2, 4L)));

        assertThat(vector.euclideanDistance(other)).isEqualByComparingTo(2.8284271247461903D);
    }

    @Test
    void maxDistance_should_throw_Exception_when_other_is_null() {
        assertThatNullPointerException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).maxDistance(null))
            .withMessage("other").withNoCause();
    }

    @Test
    void maxDistance_should_throw_Exception_when_sizes_are_unequal() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 0L)));

        assertThatIllegalArgumentException().isThrownBy(() -> vector.maxDistance(other))
            .withMessage("equal sizes expected but 1 != 2").withNoCause();
    }

    @Test
    void maxDistance_should_calculate_maxDistance() {
        final var vector = new LongVector(2, List.of(new VectorEntry<>(1, 1L), new VectorEntry<>(2, 1L)));
        final var other = new LongVector(2, List.of(new VectorEntry<>(1, 2L), new VectorEntry<>(2, 3L)));

        assertThat(vector.maxDistance(other)).isEqualByComparingTo(2.0D);
    }

    @Test
    void getElement_should_throw_Exception_when_index_is_0() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getElement(0))
            .withMessage("0 < index <= 1 expected but index = 0").withNoCause();
    }

    @Test
    void getElement_should_throw_Exception_when_index_is_2() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getElement(2))
            .withMessage("0 < index <= 1 expected but index = 2").withNoCause();
    }

    @Test
    void getElement_should_return_element() {
        assertThat(new LongVector(2, List.of(new VectorEntry<>(1, 0L), new VectorEntry<>(2, 1L))).getElement(1))
            .isZero();
    }

    @Test
    void getEntry_should_throw_Exception_when_index_is_0() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getEntry(0))
            .withMessage("0 < index <= 1 expected but index = 0").withNoCause();
    }

    @Test
    void getEntry_should_throw_Exception_when_index_is_2() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).getEntry(2))
            .withMessage("0 < index <= 1 expected but index = 2").withNoCause();
    }

    @Test
    void getEntry_should_return_entry() {
        final var entry = new VectorEntry<>(1, 0L);

        assertThat(new LongVector(2, List.of(entry, new VectorEntry<>(2, 1L))).getEntry(1)).isSameAs(entry);
    }

    @Test
    void contains_should_throw_Exception_when_element_is_null() {
        assertThatNullPointerException()
            .isThrownBy(() -> new LongVector(1, List.of(new VectorEntry<>(1, 0L))).contains(null))
            .withMessage("element").withNoCause();
    }

    @Test
    void contains_should_return_false_when_element_is_not_present() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).contains(1L)).isFalse();
    }

    @Test
    void contains_should_return_true_when_element_is_present() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).contains(0L)).isTrue();
    }

    @Test
    void hashCode_should_return_hashCode() {
        final var entries = List.of(new VectorEntry<>(1, 0L));

        assertThat(new LongVector(1, entries).hashCode()).isEqualByComparingTo(hash(entries));
    }

    @Test
    void equals_should_return_true_when_this_is_same_as_obj() {
        final var vector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThat(vector.equals(vector)).isTrue();
    }

    @Test
    void equals_should_return_false_when_obj_is_null() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).equals(null)).isFalse();
    }

    @Test
    void equals_should_return_false_when_classes_are_not_the_same() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))).equals(new Object())).isFalse();
    }

    @Test
    void equals_should_return_false_when_entries_are_unequal() {
        final var longVector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var obj = new LongVector(1, List.of(new VectorEntry<>(1, 1L)));

        assertThat(longVector.equals(obj)).isFalse();
    }

    @Test
    void equals_should_return_true_when_entries_are_equal() {
        final var longVector = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));
        final var obj = new LongVector(1, List.of(new VectorEntry<>(1, 0L)));

        assertThat(longVector.equals(obj)).isTrue();
    }

    @Test
    void toString_should_return_toString() {
        assertThat(new LongVector(1, List.of(new VectorEntry<>(1, 0L))))
            .hasToString("LongVector{entries=[VectorEntry[index=1, element=0]]}");
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

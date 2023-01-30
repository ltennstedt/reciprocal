package reciprocal.linear.vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

final class VectorEntryTests {
    @Test
    void constructor_should_throw_Exception_when_index_is_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> new VectorEntry<>(0, 0))
            .withMessage("index > 0 expected but index = 0").withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_element_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new VectorEntry<Integer>(1, null)).withMessage("element")
            .withNoCause();
    }

    @Test
    void withIndex_should_throw_Exception_when_newIndex_is_0() {
        final var entry = new VectorEntry<>(1, 0);

        assertThatIllegalArgumentException().isThrownBy(() -> entry.withIndex(0))
            .withMessage("index > 0 expected but index = 0").withNoCause();
    }

    @Test
    void withIndex_should_return_copy() {
        final var entry = new VectorEntry<>(1, 0);

        final var actual = entry.withIndex(2);

        assertThat(actual.index()).isEqualByComparingTo(2);
        assertThat(actual.element()).isZero();
    }

    @Test
    void withElement_should_throw_Exception_when_newElement_is_null() {
        final var entry = new VectorEntry<>(1, 0);

        assertThatNullPointerException().isThrownBy(() -> entry.withElement(null)).withMessage("newElement")
            .withNoCause();
    }

    @Test
    void withElement_should_return_copy() {
        final var entry = new VectorEntry<>(1, 0);

        final var actual = entry.withElement(1);

        assertThat(actual.index()).isOne();
        assertThat(actual.element()).isOne();
    }
}

package reciprocal.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.security.SecureRandom;
import org.junit.jupiter.api.Test;

final class AbstractRandomTests {
    @Test
    void constructor_should_throw_Exception_when_seed_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigIntegerRandom((byte[]) null)).withMessage("seed")
            .withNoCause();
    }

    @Test
    void constructor_should_throw_Exception_when_secureRandom_is_null() {
        assertThatNullPointerException().isThrownBy(() -> new BigIntegerRandom((SecureRandom) null))
            .withMessage("secureRandom").withNoCause();
    }

    @Test
    void toString_should_return_toString() {
        assertThat(new BigIntegerRandom()).hasToString("BigIntegerRandom{secureRandom=NativePRNG}");
    }
}

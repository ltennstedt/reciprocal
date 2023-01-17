package reciprocal.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
final class BigIntegerRandomTests {
    @InjectMocks
    private BigIntegerRandom bigIntegerRandom;

    @Mock
    private SecureRandom secureRandom;

    @Test
    void next_should_return_next_number() {
        when(secureRandom.nextLong()).thenReturn(1L);

        final var actual = bigIntegerRandom.next();

        verify(secureRandom).nextLong();
        assertThat(actual).isOne();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(secureRandom);
    }
}

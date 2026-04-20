package pl.milosnicyit.codewarehousebackend.exeptions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class UserAlreadyExistsExceptionTest {

    @Test
    void shouldDisplayCorrectMessageWithUsername() {
        String username = "testowyUser";

        assertThatThrownBy(() -> {
            throw new UserAlreadyExistsException(username);
        })
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Username testowyUser already exists");
    }

    @Test
    void shouldThrowNullPointerExceptionWhenUsernameIsNull() {
        assertThatThrownBy(() -> {
            new UserAlreadyExistsException(null);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("username is marked non-null but is null");
    }
}
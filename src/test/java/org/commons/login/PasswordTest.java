package org.commons.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {
    @Test
    void createPasswordWhenValid() {
        String validPassword = "BardzoSilneHaslo123!";

        Password password = new Password(validPassword);

        assertEquals(validPassword, password.toString());
    }

    @Test
    void shouldCreatePasswordWithExactlyFifteenCharacters() {
        String validPassword = "123456789012345";

        Password password = new Password(validPassword);

        assertEquals(validPassword, password.toString());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThrows(NullPointerException.class, () -> new Password(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "haslo", "12345678901234"})
    void shouldThrowExceptionWhenPasswordIsTooShort(String invalidPassword) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Password(invalidPassword));

        assertEquals("Password must have at least 15 characters", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"BardzoSilne Haslo123", "1234567890 12345", "               ", " spacjaNaPoczatku123"})
    void shouldThrowExceptionWhenPasswordContainsSpaces(String invalidPassword) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Password(invalidPassword));

        assertEquals("Password must not contain spaces", exception.getMessage());
    }
}
package pl.milosnicyit.codewarehousebackend.jwt.secret;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JWTSecretTest {
    @Test
    void shouldCreateJWTSecretWhenValid() {
        String validSecret = "BardzoTajnySekretJWT123!@#";

        JWTSecret jwtSecret = new JWTSecret(validSecret);

        assertEquals(validSecret, jwtSecret.toString());
    }

    @Test
    void shouldCreateJWTSecretWithExactlyFifteenCharacters() {
        String validSecret = "123456789012345";

        JWTSecret jwtSecret = new JWTSecret(validSecret);

        assertEquals(validSecret, jwtSecret.toString());
    }

    @Test
    void shouldThrowExceptionWhenSecretIsNull() {
        assertThrows(NullPointerException.class, () -> new JWTSecret(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "tajny", "12345678901234"})
    void shouldThrowExceptionWhenSecretIsTooShort(String invalidSecret) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new JWTSecret(invalidSecret));

        assertEquals("Secret must be at least 15 characters", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bardzo TajnySekret", "1234567890 12345", "               ", " spacjaNaPoczatku1"})
    void shouldThrowExceptionWhenSecretContainsSpaces(String invalidSecret) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new JWTSecret(invalidSecret));

        assertEquals("Secret must not contain spaces", exception.getMessage());
    }
}
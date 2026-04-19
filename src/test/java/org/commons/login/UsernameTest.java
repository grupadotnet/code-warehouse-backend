package org.commons.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsernameTest {
    @ParameterizedTest
    @ValueSource(strings = {"test", "%^#$#S"})
    void shouldCreateUsernameWhenValid(String validName) {
        Username username = new Username(validName);

        assertEquals(validName, username.toString());
    }

    @Test
    void shouldCreateUsernameWithExactlyThreeCharacters() {
        String validName = "jan";

        Username username = new Username(validName);

        assertEquals(validName, username.toString());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        assertThrows(NullPointerException.class, () -> new Username(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "ab", "@"})
    void shouldThrowExceptionWhenUsernameIsTooShort(String invalidName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Username(invalidName));

        assertEquals("Username must have at least 3 characters", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" test", " &*( ", " test ", "test "})
    void shouldThrowExceptionWhenUsernameContainsSpace(String invalidName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Username(invalidName));

        assertEquals("Username must not contain spaces", exception.getMessage());
    }
}
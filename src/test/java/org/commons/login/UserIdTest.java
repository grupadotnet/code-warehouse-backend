package org.commons.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserIdTest {

    @Test
    @DisplayName("Should create UserId when valid string is provided")
    void shouldCreateUserId() {
        String expectedValue = "user-123";

        UserId userId = new UserId(expectedValue);

        assertThat(userId.toString()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("Should throw NullPointerException when userId is null")
    void shouldThrowExceptionWhenNull() {
        assertThatThrownBy(() -> new UserId(null))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    @DisplayName("Should throw IllegalArgumentException when userId is empty or blank")
    void shouldThrowExceptionWhenEmpty(String emptyValue) {
        assertThatThrownBy(() -> new UserId(emptyValue.trim()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userId is empty");
    }
}
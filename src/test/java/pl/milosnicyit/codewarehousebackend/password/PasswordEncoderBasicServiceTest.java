package pl.milosnicyit.codewarehousebackend.password;

import org.commons.login.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderBasicServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    private PasswordEncoderBasicService passwordEncoderService;

    @BeforeEach
    void setUp() {
        passwordEncoderService = new PasswordEncoderBasicService(passwordEncoder);
    }

    @Test
    void shouldEncodePassword() {
        // given
        String rawPasswordString = "mojeSuperHaslo123";
        String encodedPassword = "$2a$10$wypVjTq...ZaszyfrowaneHaslo";

        Password mockPassword = mock(Password.class);
        when(mockPassword.toString()).thenReturn(rawPasswordString);

        when(passwordEncoder.encode(rawPasswordString)).thenReturn(encodedPassword);

        // when
        String result = passwordEncoderService.encode(mockPassword);

        // then
        assertEquals(encodedPassword, result);
        verify(passwordEncoder, times(1)).encode(rawPasswordString);
    }

    @Test
    void shouldReturnTrueWhenPasswordsMatch() {
        // given
        String rawPasswordString = "mojeSuperHaslo123";
        String encodedPasswordFromDb = "$2a$10$wypVjTq...ZaszyfrowaneHaslo";

        Password mockPassword = mock(Password.class);
        when(mockPassword.toString()).thenReturn(rawPasswordString);

        when(passwordEncoder.matches(rawPasswordString, encodedPasswordFromDb)).thenReturn(true);

        boolean isMatch = passwordEncoderService.matches(mockPassword, encodedPasswordFromDb);

        // then
        assertTrue(isMatch);
        verify(passwordEncoder, times(1)).matches(rawPasswordString, encodedPasswordFromDb);
    }

    @Test
    void shouldReturnFalseWhenPasswordsDoNotMatch() {
        // given
        String rawPasswordString = "zleHaslo";
        String encodedPasswordFromDb = "$2a$10$wypVjTq...ZaszyfrowaneHaslo";

        Password mockPassword = mock(Password.class);
        when(mockPassword.toString()).thenReturn(rawPasswordString);

        when(passwordEncoder.matches(rawPasswordString, encodedPasswordFromDb)).thenReturn(false);

        // when
        boolean isMatch = passwordEncoderService.matches(mockPassword, encodedPasswordFromDb);

        // then
        assertFalse(isMatch);
        verify(passwordEncoder, times(1)).matches(rawPasswordString, encodedPasswordFromDb);
    }
}
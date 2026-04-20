package pl.milosnicyit.codewarehousebackend.users;

import org.commons.login.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import pl.milosnicyit.codewarehousebackend.exeptions.UserAlreadyExistsException;
import pl.milosnicyit.codewarehousebackend.jwt.JWTService;
import pl.milosnicyit.codewarehousebackend.password.PasswordEncoderService;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserDTO;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserRepositoryWrapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAppServiceTest {

    @Mock
    private UserRepositoryWrapper userRepositoryWrapper;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserAppService userAppService;

    @Test
    void shouldThrowExceptionWhenUsernameIsAlreadyTaken() {
        String username = "zajetyLogin";
        String rawPassword = "secretPassworddddddddddddddddddddddddddddddddddddd";

        when(userRepositoryWrapper.existsByUsername(username)).thenReturn(true);
        assertThrows(
                UserAlreadyExistsException.class,
                () -> userAppService.registerUser(username, rawPassword)
        );

        verify(userRepositoryWrapper, never()).save(any());
        verify(jwtService, never()).generateToken(anyString());
    }

    @Test
    void shouldRegisterUserAndReturnTokenWhenDataIsCorrect() {
        String username = "nowyUser";
        String rawPassword = "secretPasswodddddddddddddddddddddddd";
        String encodedPassword = "encodedPassword123";
        String expectedToken = "jwt.token.here";
        String userId = "aaa";

        when(userRepositoryWrapper.existsByUsername(username)).thenReturn(false);
        when(passwordEncoderService.encode(any(Password.class))).thenReturn(encodedPassword);
        when(userRepositoryWrapper.save(any(UserDTO.class))).thenReturn(true);
        when(userRepositoryWrapper.findByUsername(any(String.class))).thenReturn(new UserDTO(userId, username,
                encodedPassword));
        when(jwtService.generateToken(userId)).thenReturn(expectedToken);

        String result = userAppService.registerUser(username, rawPassword);

        assertEquals(expectedToken, result, "Should return generated JWT token");

        ArgumentCaptor<UserDTO> userDtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userRepositoryWrapper).save(userDtoCaptor.capture());

        UserDTO capturedUser = userDtoCaptor.getValue();
        assertEquals(username, capturedUser.getUsername(), "Saved user should have correct username");
        assertEquals(encodedPassword, capturedUser.getPassword(), "Saved user should have ENCODED password");
    }

    @Test
    void shouldReturnNullWhenDatabaseSaveFails() {
        String username = "nowyUser";
        String rawPassword = "secretPdddddddddddddddddddddddddddddassword";
        String encodedPassword = "encodedPassword123";

        when(userRepositoryWrapper.existsByUsername(username)).thenReturn(false);
        when(passwordEncoderService.encode(any(Password.class))).thenReturn(encodedPassword);

        when(userRepositoryWrapper.save(any(UserDTO.class))).thenReturn(false);

        String result = userAppService.registerUser(username, rawPassword);

        assertNull(result, "Should return null if saving to DB fails");

        verify(jwtService, never()).generateToken(anyString());
    }

    @Test
    void shouldLoginUserAndReturnTokenWhenCredentialsAreCorrect() {
        String userId = "ddddddd";
        String username = "existingUser";
        String rawPassword = "correctPassword123";
        String encodedPasswordInDb = "encodedHash";
        String expectedToken = "valid.jwt.token";

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername(username);
        userDTO.setPassword(encodedPasswordInDb);

        when(userRepositoryWrapper.findByUsername(username)).thenReturn(userDTO);
        when(passwordEncoderService.matches(any(Password.class), eq(encodedPasswordInDb))).thenReturn(true);
        when(jwtService.generateToken(userId)).thenReturn(expectedToken);

        String result = userAppService.loginUser(username, rawPassword);

        assertEquals(expectedToken, result);
        verify(passwordEncoderService).matches(any(Password.class), eq(encodedPasswordInDb));
        verify(jwtService).generateToken(userId);
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        String username = "existingUser";
        String rawPassword = "wrongPasswosssssssssssssssssssssrd";
        String encodedPasswordInDb = "encssssssssssssodedHash";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(encodedPasswordInDb);

        when(userRepositoryWrapper.findByUsername(username)).thenReturn(userDTO);
        when(passwordEncoderService.matches(any(Password.class), eq(encodedPasswordInDb))).thenReturn(false);

        assertThrows(
                BadCredentialsException.class,
                () -> userAppService.loginUser(username, rawPassword)
        );

        verify(jwtService, never()).generateToken(anyString());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenLoginArgumentsAreNull() {
        assertThrows(NullPointerException.class, () -> userAppService.loginUser(null, "pass"));
        assertThrows(NullPointerException.class, () -> userAppService.loginUser("user", null));
    }
}
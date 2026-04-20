package pl.milosnicyit.codewarehousebackend.jwt;

import org.commons.login.UserId;
import org.commons.login.Username;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppJwtServiceTest {

    @Mock
    private JWTSecretService jwtSecretService;

    @Test
    void shouldDelegateGenerateToken() {
        try (MockedConstruction<JwtBasicService> mockedConstruction = Mockito.mockConstruction(JwtBasicService.class,
                (mock, context) -> {
                    when(mock.generateToken(any(UserId.class))).thenReturn("mocked_token");
                })) {

            AppJwtService appJwtService = new AppJwtService(jwtSecretService);
            String inputUsername = "testUser";

            String result = appJwtService.generateToken(inputUsername);

            assertEquals("mocked_token", result);

            JwtBasicService createdMock = mockedConstruction.constructed().get(0);
            verify(createdMock).generateToken(Mockito.argThat(arg -> arg.toString().equals(inputUsername)));
        }
    }

    @Test
    void shouldDelegateExtractLogin() {
        // given
        try (MockedConstruction<JwtBasicService> mockedConstruction = Mockito.mockConstruction(JwtBasicService.class,
                (mock, context) -> {
                    when(mock.extractUserId("some_token")).thenReturn(new UserId("extractedUser"));
                })) {

            AppJwtService appJwtService = new AppJwtService(jwtSecretService);

            String result = appJwtService.extractUserId("some_token");

            assertEquals("extractedUser", result);
        }
    }

    @Test
    void shouldDelegateValidateToken() {
        // given
        try (MockedConstruction<JwtBasicService> mockedConstruction = Mockito.mockConstruction(JwtBasicService.class,
                (mock, context) -> {
                    when(mock.validateToken("valid_token")).thenReturn(true);
                })) {

            AppJwtService appJwtService = new AppJwtService(jwtSecretService);

            boolean result = appJwtService.validateToken("valid_token");

            assertTrue(result);
        }
    }
}
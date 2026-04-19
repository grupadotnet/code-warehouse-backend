package pl.milosnicyit.codewarehousebackend.jwt;

import org.commons.login.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtBasicServiceTest {
    private static final String MOCK_SECRET = "ToJestBardzoTajnyKluczTestowyKtoryMaMin32Znaki!!";
    private static final String TEST_USERNAME = "testUser123";

    @Mock
    private JWTSecretService jwtSecretService;

    private JwtBasicService jwtBasicService;

    @BeforeEach
    void setUp() {
        when(jwtSecretService.getSecret()).thenReturn(MOCK_SECRET);
        jwtBasicService = new JwtBasicService(jwtSecretService);
    }

    @Test
    void shouldGenerateValidToken() {
        Username username = mock(Username.class);
        when(username.toString()).thenReturn(TEST_USERNAME);

        String token = jwtBasicService.generateToken(username);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void shouldExtractLoginFromToken() {
        Username mockUsername = mock(Username.class);
        when(mockUsername.toString()).thenReturn(TEST_USERNAME);

        String generatedToken = jwtBasicService.generateToken(mockUsername);

        Username extractedUsername = jwtBasicService.extractLogin(generatedToken);

        assertNotNull(extractedUsername);
        assertEquals(TEST_USERNAME, extractedUsername.toString());
    }

    @Test
    void shouldReturnTrueWhenTokenIsValid() {
        Username mockUsername = mock(Username.class);
        when(mockUsername.toString()).thenReturn(TEST_USERNAME);
        String generatedToken = jwtBasicService.generateToken(mockUsername);

        boolean isValid = jwtBasicService.validateToken(generatedToken);

        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenTokenIsTampered() {
        // given
        Username mockUsername = mock(Username.class);
        when(mockUsername.toString()).thenReturn(TEST_USERNAME);
        String generatedToken = jwtBasicService.generateToken(mockUsername);

        String tamperedToken = generatedToken.substring(0, generatedToken.length() - 3) + "BAD";

        boolean isValid = jwtBasicService.validateToken(tamperedToken);

        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalseForMalformedString() {
        String invalidToken = "to.nie.jest.prawdziwy.token";

        boolean isValid = jwtBasicService.validateToken(invalidToken);

        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalseForEmptyToken() {
        String emptyToken = "";

        boolean isValid = jwtBasicService.validateToken(emptyToken);

        assertFalse(isValid);
    }
}
package pl.milosnicyit.codewarehousebackend.jwt;

import org.commons.login.UserId;
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
        UserId userId = mock(UserId.class);
        when(userId.toString()).thenReturn(TEST_USERNAME);

        String token = jwtBasicService.generateToken(userId);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void shouldExtractLoginFromToken() {
        UserId userId = mock(UserId.class);
        when(userId.toString()).thenReturn(TEST_USERNAME);

        String generatedToken = jwtBasicService.generateToken(userId);

        UserId extractedUserId = jwtBasicService.extractUserId(generatedToken);

        assertNotNull(extractedUserId);
        assertEquals(TEST_USERNAME, extractedUserId.toString());
    }

    @Test
    void shouldReturnTrueWhenTokenIsValid() {
        UserId userId = mock(UserId.class);
        when(userId.toString()).thenReturn(TEST_USERNAME);
        String generatedToken = jwtBasicService.generateToken(userId);

        boolean isValid = jwtBasicService.validateToken(generatedToken);

        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenTokenIsTampered() {
        UserId userId = mock(UserId.class);
        when(userId.toString()).thenReturn(TEST_USERNAME);
        String generatedToken = jwtBasicService.generateToken(userId);

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
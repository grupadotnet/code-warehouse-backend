package pl.milosnicyit.codewarehousebackend.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JWTServiceSpecificationTest {

    @Mock
    private JWTSecretService jwtSecretService;

    @Test
    void shouldCreateJwtServiceBean() {
        JWTServiceConfiguration configuration = new JWTServiceConfiguration(jwtSecretService);

        JWTService jwtService = configuration.jwtService();

        assertNotNull(jwtService, "JWTService bean should not be null");
        assertInstanceOf(AppJwtService.class, jwtService, "Bean should be an instance of AppJwtService");
    }
}
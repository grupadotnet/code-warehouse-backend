package pl.milosnicyit.codewarehousebackend.jwt.secret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class JETSecretSpecificationTest {
    @Test
    void shouldReturnJWTDevService() {
        JWTSecretConfiguration configuration = new JWTSecretConfiguration();

        JWTSecretService service = configuration.jwtSecretService();

        assertInstanceOf(JWTDevService.class, service);
    }
}
package pl.milosnicyit.codewarehousebackend.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

@Configuration
public class JWTServiceConfiguration {
    private final JWTSecretService jwtSecretService;

    @Autowired
    public JWTServiceConfiguration(JWTSecretService jwtSecretService) {
        this.jwtSecretService = jwtSecretService;
    }

    @Bean
    public JWTService jwtService() {
        return new AppJwtService(this.jwtSecretService);
    }
}

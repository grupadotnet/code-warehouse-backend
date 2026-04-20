package pl.milosnicyit.codewarehousebackend.jwt.secret;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTSecretConfiguration {

    @Bean
    public JWTSecretService jwtSecretService() {
        return new JWTDevService();
    }
}

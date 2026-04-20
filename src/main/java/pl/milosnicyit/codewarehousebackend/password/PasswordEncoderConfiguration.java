package pl.milosnicyit.codewarehousebackend.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderConfiguration() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoderService passwordEncoderService() {
        return new PasswordEncoderBasicService(passwordEncoder);
    }
}

package pl.milosnicyit.codewarehousebackend.password;

import org.commons.login.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordEncoderBasicService implements PasswordEncoderService {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderBasicService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(Password rawPassword) {
        return this.passwordEncoder.encode(rawPassword.toString());
    }

    public boolean matches(Password rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword.toString(), encodedPassword);
    }
}

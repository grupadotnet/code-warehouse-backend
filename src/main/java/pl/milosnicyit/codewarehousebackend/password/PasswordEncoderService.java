package pl.milosnicyit.codewarehousebackend.password;

import org.commons.login.Password;

public interface PasswordEncoderService {
    String encode(Password rawPassword);

    boolean matches(Password rawPassword, String encodedPassword);
}

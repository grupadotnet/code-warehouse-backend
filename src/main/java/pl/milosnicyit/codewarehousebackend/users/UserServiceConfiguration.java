package pl.milosnicyit.codewarehousebackend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.milosnicyit.codewarehousebackend.jwt.JWTService;
import pl.milosnicyit.codewarehousebackend.password.PasswordEncoderService;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserRepositoryWrapper;

@Configuration
public class UserServiceConfiguration {
    private final UserRepositoryWrapper userRepositoryWrapper;
    private final PasswordEncoderService passwordEncoderService;
    private final JWTService jwtService;

    @Autowired
    public UserServiceConfiguration(UserRepositoryWrapper userRepositoryWrapper, PasswordEncoderService passwordEncoderService,
                                    JWTService jwtService) {
        this.userRepositoryWrapper = userRepositoryWrapper;
        this.passwordEncoderService = passwordEncoderService;
        this.jwtService = jwtService;
    }

    @Bean
    public UsersService usersService() {
        return new UserAppService(userRepositoryWrapper, passwordEncoderService, jwtService);
    }
}

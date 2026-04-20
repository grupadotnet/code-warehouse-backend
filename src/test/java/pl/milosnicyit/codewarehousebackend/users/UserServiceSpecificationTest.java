package pl.milosnicyit.codewarehousebackend.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.milosnicyit.codewarehousebackend.jwt.JWTService;
import pl.milosnicyit.codewarehousebackend.password.PasswordEncoderService;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserRepositoryWrapper;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserServiceSpecificationTest {

    @Mock
    private UserRepositoryWrapper userRepositoryWrapper;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private JWTService jwtService;

    @Test
    void shouldCreateUsersServiceBean() {
        UserServiceConfiguration configuration = new UserServiceConfiguration(
                userRepositoryWrapper,
                passwordEncoderService,
                jwtService
        );

        UsersService usersService = configuration.usersService();

        assertNotNull(usersService, "UsersService bean should not be null");
        assertInstanceOf(UserAppService.class, usersService, "Bean should be an instance of UserAppService");
    }
}
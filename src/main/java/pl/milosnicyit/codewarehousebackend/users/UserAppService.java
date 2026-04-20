package pl.milosnicyit.codewarehousebackend.users;

import lombok.NonNull;
import org.commons.login.Password;
import org.springframework.security.authentication.BadCredentialsException;
import pl.milosnicyit.codewarehousebackend.exeptions.UserAlreadyExistsException;
import pl.milosnicyit.codewarehousebackend.jwt.JWTService;
import pl.milosnicyit.codewarehousebackend.password.PasswordEncoderService;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserDTO;
import pl.milosnicyit.codewarehousebackend.users.database.wrapper.UserRepositoryWrapper;

class UserAppService implements UsersService {
    private final UserRepositoryWrapper userRepositoryWrapper;
    private final PasswordEncoderService passwordEncoderService;
    private final JWTService jwtService;

    public UserAppService(UserRepositoryWrapper userRepositoryWrapper, PasswordEncoderService passwordEncoderService,
                          JWTService jwtService) {
        this.userRepositoryWrapper = userRepositoryWrapper;
        this.passwordEncoderService = passwordEncoderService;
        this.jwtService = jwtService;
    }

    public String registerUser(@NonNull final String username, @NonNull final String rawPassword) {
        if (userRepositoryWrapper.existsByUsername(username)) {
            throw new UserAlreadyExistsException(username);
        }

        final Password password = new Password(rawPassword);
        final UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(this.passwordEncoderService.encode(password));

        if (this.userRepositoryWrapper.save(userDTO)) {
            return this.jwtService.generateToken(userDTO.getId());
        }
        return null;
    }

    public String loginUser(@NonNull final String username, @NonNull final String rawPassword) {
        final Password password = new Password(rawPassword);
        final UserDTO userDTO = this.userRepositoryWrapper.findByUsername(username);
        if (!this.passwordEncoderService.matches(password, userDTO.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return this.jwtService.generateToken(userDTO.getUsername());
    }
}

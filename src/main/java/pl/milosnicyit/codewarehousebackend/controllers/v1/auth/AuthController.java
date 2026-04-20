package pl.milosnicyit.codewarehousebackend.controllers.v1.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.milosnicyit.codewarehousebackend.users.UsersService;

import static pl.milosnicyit.codewarehousebackend.controllers.v1.RestConstant.PATH;

@RestController
@RequestMapping(PATH + "auth")
public class AuthController {
    private final UsersService usersService;

    @Autowired
    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account based on the provided credentials. Returns a JWT token, which might be null under certain conditions (e.g., pending email verification)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered if jwt is not null. Check the response body for the JWT token (can be null)."),
    })
    public ResponseEntity<TokenResponse> register(@RequestBody UserRequest userRequest) {
        final String token = this.usersService.registerUser(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}

package pl.milosnicyit.codewarehousebackend.controllers.v1.auth;

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
    public ResponseEntity<TokenResponse> register(@RequestBody UserRequest userRequest) {
        final String token = this.usersService.registerUser(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}

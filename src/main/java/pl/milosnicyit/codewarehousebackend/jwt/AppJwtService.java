package pl.milosnicyit.codewarehousebackend.jwt;

import lombok.NonNull;
import org.commons.login.Username;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

class AppJwtService implements JWTService {
    private final JwtBasicService jwtBasicService;

    public AppJwtService(@NonNull final JWTSecretService jwtSecretService) {
        this.jwtBasicService = new JwtBasicService(jwtSecretService);
    }

    @Override
    public String generateToken(@NonNull String username) {
        return this.jwtBasicService.generateToken(new Username(username));
    }

    @Override
    public String extractLogin(@NonNull String token) {
        return this.jwtBasicService.extractLogin(token).toString();
    }

    @Override
    public boolean validateToken(@NonNull String jwtToken) {
        return this.jwtBasicService.validateToken(jwtToken);
    }
}

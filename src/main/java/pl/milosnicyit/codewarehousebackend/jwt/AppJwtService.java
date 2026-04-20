package pl.milosnicyit.codewarehousebackend.jwt;

import lombok.NonNull;
import org.commons.login.UserId;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

class AppJwtService implements JWTService {
    private final JwtBasicService jwtBasicService;

    public AppJwtService(@NonNull final JWTSecretService jwtSecretService) {
        this.jwtBasicService = new JwtBasicService(jwtSecretService);
    }

    @Override
    public String generateToken(@NonNull String userId) {
        return this.jwtBasicService.generateToken(new UserId(userId));
    }

    @Override
    public String extractUserId(@NonNull String token) {
        return this.jwtBasicService.extractUserId(token).toString();
    }

    @Override
    public boolean validateToken(@NonNull String jwtToken) {
        return this.jwtBasicService.validateToken(jwtToken);
    }
}

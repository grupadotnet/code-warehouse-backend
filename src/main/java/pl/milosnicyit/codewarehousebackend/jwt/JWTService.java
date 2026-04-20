package pl.milosnicyit.codewarehousebackend.jwt;

import lombok.NonNull;

public interface JWTService {
    String generateToken(@NonNull final String userId);

    String extractUserId(@NonNull final String token);

    boolean validateToken(@NonNull final String jwtToken);
}

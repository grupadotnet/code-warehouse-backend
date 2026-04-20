package pl.milosnicyit.codewarehousebackend.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.commons.login.UserId;
import org.commons.login.Username;
import pl.milosnicyit.codewarehousebackend.jwt.secret.JWTSecretService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

class JwtBasicService {
    private final JWTSecretService jwtSecretService;

    public JwtBasicService(JWTSecretService jwtSecretService) {
        this.jwtSecretService = jwtSecretService;
    }

    public String generateToken(@NonNull final UserId userId) {
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSigningKey())
                .compact();
    }

    public UserId extractUserId(@NonNull final String token) {
        final String subject = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

        return new UserId(subject);
    }

    public boolean validateToken(@NonNull final String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwtToken);
            return true;
        } catch (final JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private SecretKey getSigningKey() {
        final byte[] secretBytes = this.jwtSecretService.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}

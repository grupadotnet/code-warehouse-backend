package pl.milosnicyit.codewarehousebackend.jwt.secret;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

class JWTDevService implements JWTSecretService {
    private final JWTSecret jwtSecret;

    public JWTDevService() {
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Encoders.BASE64.encode(secretKey.getEncoded());
        this.jwtSecret = new JWTSecret(base64Key);
    }

    @Override
    public String getSecret() {
        return this.jwtSecret.toString();
    }
}

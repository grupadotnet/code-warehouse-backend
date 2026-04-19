package pl.milosnicyit.codewarehousebackend.jwt.secret;

import lombok.NonNull;

import java.util.Objects;

class JWTSecret {
    private final String secret;

    public JWTSecret(@NonNull final String secret) {
        if (secret.length() < 15) throw new IllegalArgumentException("Secret must be at least 15 characters");
        if (secret.contains(" ")) throw new IllegalArgumentException("Secret must not contain spaces");

        this.secret = secret;
    }

    @Override
    public String toString() {
        return this.secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTSecret jwtSecret = (JWTSecret) o;
        return Objects.equals(secret, jwtSecret.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(secret);
    }
}

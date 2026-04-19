package org.commons.login;

import lombok.NonNull;

import java.util.Objects;

public class Password {
    private final String password;

    public Password(@NonNull final String password) {
        if (password.length() < 15) throw new IllegalArgumentException("Password must have at least 15 characters");
        if (password.contains(" ")) throw new IllegalArgumentException("Password must not contain spaces");

        this.password = password;
    }

    @Override
    public String toString() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}

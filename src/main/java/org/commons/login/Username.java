package org.commons.login;

import lombok.NonNull;

import java.util.Objects;

public class Username {
    private final String username;

    public Username(@NonNull final String username) {
        if (username.length() < 3) throw new IllegalArgumentException("Username must have at least 3 characters");
        if (username.contains(" ")) throw new IllegalArgumentException("Username must not contain spaces");

        this.username = username;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}

package org.commons.login;

import lombok.NonNull;

public class UserId {
    private String userId;

    public UserId(@NonNull final String userId) {
        if (userId.isEmpty()) throw new IllegalArgumentException("userId is empty");

        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.userId;
    }
}

package pl.milosnicyit.codewarehousebackend.exeptions;

import lombok.NonNull;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(@NonNull final String username) {
        super("Username " + username + " already exists");
    }
}

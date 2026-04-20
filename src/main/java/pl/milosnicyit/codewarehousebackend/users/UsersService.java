package pl.milosnicyit.codewarehousebackend.users;

import lombok.NonNull;

public interface UsersService {
    String registerUser(String username, String rawPassword);
    String loginUser(@NonNull final String username, @NonNull final String rawPassword);
}

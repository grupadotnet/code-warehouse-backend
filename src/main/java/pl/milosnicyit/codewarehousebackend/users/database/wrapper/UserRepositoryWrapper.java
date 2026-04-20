package pl.milosnicyit.codewarehousebackend.users.database.wrapper;

import lombok.NonNull;

public interface UserRepositoryWrapper {
    boolean save(@NonNull final UserDTO user);

    UserDTO findByUsername(@NonNull final String username);

    UserDTO findById(@NonNull final Long id);

    boolean existsByUsername(@NonNull final String username);
}

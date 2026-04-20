package pl.milosnicyit.codewarehousebackend.users.database.wrapper;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public interface UserRepositoryWrapper {
    boolean save(@NonNull final UserDTO user);

    @NotNull
    UserDTO findByUsername(@NonNull final String username);

    @NotNull
    UserDTO findById(@NonNull final Long id);

    boolean existsByUsername(@NonNull final String username);
}

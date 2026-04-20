package pl.milosnicyit.codewarehousebackend.users.database.wrapper;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.milosnicyit.codewarehousebackend.users.database.UserEntity;
import pl.milosnicyit.codewarehousebackend.users.database.UsersRepository;

class UserRepositoryBasicWrapper implements UserRepositoryWrapper {
    private final UsersRepository usersRepository;

    public UserRepositoryBasicWrapper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean save(@NonNull UserDTO user) {
        final UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());

        this.usersRepository.save(userEntity);
        return true;
    }

    @Override
    public UserDTO findByUsername(@NonNull String username) {
        final UserEntity userEntity = this.usersRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username " + username + " not found"));
        return new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }

    @Override
    public UserDTO findById(@NonNull Long id) {
        final UserEntity userEntity = this.usersRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
        return new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }

    @Override
    public boolean existsByUsername(@NonNull String username) {
        final UserEntity userEntity = this.usersRepository.findByUsername(username).orElse(null);
        return userEntity != null;
    }
}

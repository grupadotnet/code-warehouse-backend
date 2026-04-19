package pl.milosnicyit.codewarehousebackend.users.database.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.milosnicyit.codewarehousebackend.users.database.UsersRepository;

@Configuration
public class UserRepositoryConfiguration {
    private final UsersRepository usersRepository;

    @Autowired
    public UserRepositoryConfiguration(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Bean
    public UserRepositoryWrapper userRepositoryWrapper() {
        return new UserRepositoryBasicWrapper(this.usersRepository);
    }
}

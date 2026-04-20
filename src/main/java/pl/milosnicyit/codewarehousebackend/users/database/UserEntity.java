package pl.milosnicyit.codewarehousebackend.users.database;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", unique = true, nullable = false)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}

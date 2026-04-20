package pl.milosnicyit.codewarehousebackend.location.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milosnicyit.codewarehousebackend.location.Location;
import java.util.Optional;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
}
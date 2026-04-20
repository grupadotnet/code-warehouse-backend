package pl.milosnicyit.codewarehousebackend.location.database.wrapper;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milosnicyit.codewarehousebackend.location.Location;
import java.util.Optional;

interface LocationJpaRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
}
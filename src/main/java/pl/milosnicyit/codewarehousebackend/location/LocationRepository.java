package pl.milosnicyit.codewarehousebackend.location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    List<Location> findAll();
    Optional<Location> findById(Long id);
    Optional<Location> findByName(String name);
    Location save(Location location);
    void deleteById(Long id);
}
package pl.milosnicyit.codewarehousebackend.location.database.wrapper;

import org.springframework.stereotype.Repository;
import pl.milosnicyit.codewarehousebackend.location.Location;
import pl.milosnicyit.codewarehousebackend.location.LocationRepository;

import java.util.List;
import java.util.Optional;

@Repository
class LocationRepositoryWrapper implements LocationRepository {

    private final LocationJpaRepository jpaRepository;

    public LocationRepositoryWrapper(LocationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Location> findAll() { return jpaRepository.findAll(); }

    @Override
    public Optional<Location> findById(Long id) { return jpaRepository.findById(id); }

    @Override
    public Optional<Location> findByName(String name) { return jpaRepository.findByName(name); }

    @Override
    public Location save(Location location) { return jpaRepository.save(location); }

    @Override
    public void deleteById(Long id) { jpaRepository.deleteById(id); }
}
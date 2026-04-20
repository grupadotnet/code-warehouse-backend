package pl.milosnicyit.codewarehousebackend.location.database.wrapper;

import org.springframework.stereotype.Repository;
import pl.milosnicyit.codewarehousebackend.location.Location;
import pl.milosnicyit.codewarehousebackend.location.LocationRepository;
import pl.milosnicyit.codewarehousebackend.location.database.LocationEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
class LocationRepositoryWrapper implements LocationRepository {

    private final LocationJpaRepository jpaRepository;

    LocationRepositoryWrapper(LocationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Location> findAll() {
        return jpaRepository.findAll().stream()
                .map(LocationEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Location> findById(Long id) {
        return jpaRepository.findById(id).map(LocationEntity::toDomain);
    }

    @Override
    public Optional<Location> findByName(String name) {
        return jpaRepository.findByName(name).map(LocationEntity::toDomain);
    }

    @Override
    public Location save(Location location) {
        LocationEntity entity = LocationEntity.fromDomain(location);
        LocationEntity savedEntity = jpaRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
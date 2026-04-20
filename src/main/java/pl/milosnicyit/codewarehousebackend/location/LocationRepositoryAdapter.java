package pl.milosnicyit.codewarehousebackend.location.adapter;

import org.springframework.stereotype.Repository;
import pl.milosnicyit.codewarehousebackend.location.Location;
import pl.milosnicyit.codewarehousebackend.location.LocationRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepositoryAdapter implements LocationRepository {

    private final LocationJpaRepository jpaRepository;

    public LocationRepositoryAdapter(LocationJpaRepository jpaRepository) {
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
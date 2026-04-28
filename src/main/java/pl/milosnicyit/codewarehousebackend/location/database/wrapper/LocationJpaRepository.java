package pl.milosnicyit.codewarehousebackend.location.database.wrapper;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.milosnicyit.codewarehousebackend.location.database.LocationEntity;

import java.util.Optional;

interface LocationJpaRepository extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findByName(String name);
}
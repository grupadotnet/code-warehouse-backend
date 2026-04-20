package pl.milosnicyit.codewarehousebackend.location.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.milosnicyit.codewarehousebackend.location.Location;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public static LocationEntity fromDomain(Location location) {
        return new LocationEntity(location.getId(), location.getName());
    }

    public Location toDomain() {
        return new Location(id, name);
    }
}
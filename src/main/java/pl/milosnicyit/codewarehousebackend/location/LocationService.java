package pl.milosnicyit.codewarehousebackend.location;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }
    public Location updateLocationName(Long id, String newName) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lokalizacja nie istnieje"));
        existingLocation.setNazwaLokalizacji(newName);
        return locationRepository.save(existingLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}

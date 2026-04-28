package pl.milosnicyit.codewarehousebackend.location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll().stream()
                .filter(Location::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Location createLocation(Location location) {
        if (locationRepository.findByName(location.getName()).isPresent()) {
            throw new IllegalArgumentException("Location with this name already exists");
        }
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocationName(Long id, String newName) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        existingLocation.setName(newName);
        return locationRepository.save(existingLocation);
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        location.deactivate();
        locationRepository.save(location);
    }
}
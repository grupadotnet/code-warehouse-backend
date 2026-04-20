package pl.milosnicyit.codewarehousebackend.location;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    Location createLocation(Location location);
    Location updateLocationName(Long id, String newName);
    void deleteLocation(Long id);
}
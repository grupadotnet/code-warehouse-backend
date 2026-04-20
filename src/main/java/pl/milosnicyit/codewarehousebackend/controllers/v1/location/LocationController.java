package pl.milosnicyit.codewarehousebackend.controllers.v1.location;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.milosnicyit.codewarehousebackend.location.Location;
import pl.milosnicyit.codewarehousebackend.location.LocationService;

@RestController
@RequestMapping("/api/locations")
class LocationController {

  private final LocationService locationService;

  LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  ResponseEntity<List<Location>> getLocations() {
    return ResponseEntity.ok(locationService.getAllLocations());
  }
  @PostMapping
  ResponseEntity<Location>
  createLocation(@RequestBody Location location) {
    return ResponseEntity.ok(locationService.createLocation(location));
  }
  @PatchMapping("/{id}")
  ResponseEntity<Location>
  updateLocation(@PathVariable Long id, @RequestBody @NonNull Location locationUpdate) {
    return ResponseEntity.ok(
        locationService.updateLocationName(id, locationUpdate.getName()));
  }
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
    locationService.deleteLocation(id);
    return ResponseEntity.noContent().build();
  }
}

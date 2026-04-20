package pl.milosnicyit.codewarehousebackend.location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceSpecificationTest {

    @Mock
    private LocationRepository locationRepository;

    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService = new LocationServiceImpl(locationRepository);
    }

    @Test
    void shouldCreateLocationWhenNameIsUnique() {
        Location location = new Location(1L, "Magazyn");
        when(locationRepository.findByName("Magazyn")).thenReturn(Optional.empty());
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location result = locationService.createLocation(location);

        assertEquals("Magazyn", result.getName());
        verify(locationRepository).save(location);
    }

    @Test
    void shouldThrowExceptionWhenLocationNameExists() {
        Location location = new Location(1L, "Magazyn");
        when(locationRepository.findByName("Magazyn")).thenReturn(Optional.of(location));

        assertThrows(IllegalArgumentException.class, () -> locationService.createLocation(location));
    }
}
package pl.milosnicyit.codewarehousebackend.location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void shouldDeactivateLocationWhenEmpty() {
        // given (empty = true)
        Location location = new Location(1L, "Magazyn", true, true);
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));

        // when
        locationService.deleteLocation(1L);

        // then
        assertFalse(location.isActive());
        verify(locationRepository).save(location);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonEmptyLocation() {
        // given (empty = false)
        Location location = new Location(1L, "Magazyn", true, false);
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));

        // when / then
        assertThrows(IllegalStateException.class, () -> locationService.deleteLocation(1L));
    }
}
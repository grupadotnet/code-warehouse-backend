package pl.milosnicyit.codewarehousebackend.location.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.milosnicyit.codewarehousebackend.location.LocationRepository;
import pl.milosnicyit.codewarehousebackend.location.LocationService;
import pl.milosnicyit.codewarehousebackend.location.LocationServiceImpl;

@Configuration
public class LocationConfiguration {

    @Bean
    public LocationService locationService(LocationRepository locationRepository) {
        return new LocationServiceImpl(locationRepository);
    }
}
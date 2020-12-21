package io.github.batetolast1.wedderforecast.service.location.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.repository.location.LocationRepository;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import io.github.batetolast1.wedderforecast.service.location.PostalCoordinateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

@Log4j2
@RequiredArgsConstructor
public class DefaultLocationService implements LocationService {

    private final PostalCoordinateService postalCoordinateService;

    private final LocationRepository locationRepository;

    @Override
    public Location getPersistedLocation(Location location) {
        Optional<Location> optionalLocation = locationRepository.findByPlaceId(location.getPlaceId());

        if (optionalLocation.isPresent()) {
            log.debug("Location found in DB");
            return optionalLocation.get();
        }

        log.debug("Location not found in DB");
        location.setPostalCoordinate(postalCoordinateService.getPersistedPostalCoordinate(location.getPostalCoordinate()));
        return locationRepository.save(location);
    }
}

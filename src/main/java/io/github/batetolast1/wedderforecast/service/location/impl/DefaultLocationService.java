package io.github.batetolast1.wedderforecast.service.location.impl;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.repository.location.LocationRepository;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

@Log4j2
@RequiredArgsConstructor
public class DefaultLocationService implements LocationService {

    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    @Override
    public Location getLocationByPostalCodeAndCountryCode(LocationDto locationDto) {
        Optional<Location> optionalLocation = locationRepository.findFirstByPostalCodeAndCountryCode(locationDto.getPostalCode(), locationDto.getCountryCode());
        if (optionalLocation.isPresent()) {
            log.debug("Location found in DB");
            return optionalLocation.get();
        }
        log.debug("Location not found in DB");
        return locationRepository.save(modelMapper.map(locationDto, Location.class));
    }

    @Override
    public Location getLocationByPlaceId(LocationDto locationDto) {
        Optional<Location> optionalLocation = locationRepository.findByPlaceId(locationDto.getPlaceId());
        if (optionalLocation.isPresent()) {
            log.debug("Location found in DB");
            return optionalLocation.get();
        }
        log.debug("Location not found in DB");
        return locationRepository.save(modelMapper.map(locationDto, Location.class));
    }

    @Override
    public Location getPersistedLocation(Location location) {
        Optional<Location> optionalLocation = locationRepository.findByPlaceId(location.getPlaceId());
        if (optionalLocation.isPresent()) {
            log.debug("Location found in DB");
            return optionalLocation.get();
        }
        log.debug("Location not found in DB");
        return locationRepository.save(location);
    }
}

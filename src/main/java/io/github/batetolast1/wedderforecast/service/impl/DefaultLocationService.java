package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.repository.location.LocationRepository;
import io.github.batetolast1.wedderforecast.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class DefaultLocationService implements LocationService {

    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    @Override
    public Location getLocation(LocationDto locationDto) {
        return locationRepository
                .findByPostalCodeAndCountryCode(locationDto.getCountryCode(), locationDto.getPostalCode())
                .orElse(locationRepository.save(modelMapper.map(locationDto, Location.class)));
    }
}

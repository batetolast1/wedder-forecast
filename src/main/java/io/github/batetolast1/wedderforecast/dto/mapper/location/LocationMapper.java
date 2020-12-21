package io.github.batetolast1.wedderforecast.dto.mapper.location;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.model.location.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class LocationMapper {

    private final PostalCoordinateMapper postalCoordinateMapper;

    public Location toLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setPlaceId(locationDto.getPlaceId());
        location.setFormattedAddress(locationDto.getFormattedAddress());
        location.setName(locationDto.getName());
        location.setPostalCoordinate(postalCoordinateMapper.toPostalCoordinate(locationDto.getPostalCoordinateDto()));
        return location;
    }

    public LocationDto toLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setPlaceId(location.getPlaceId());
        locationDto.setFormattedAddress(location.getFormattedAddress());
        locationDto.setName(location.getName());
        locationDto.setPostalCoordinateDto(postalCoordinateMapper.toPostalCoordinateDto(location.getPostalCoordinate()));
        return locationDto;
    }
}

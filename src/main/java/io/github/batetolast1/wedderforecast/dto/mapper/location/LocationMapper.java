package io.github.batetolast1.wedderforecast.dto.mapper.location;

import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationMapper {

    public Location toLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setPostalCode(locationDto.getPostalCode());
        location.setCountryCode(locationDto.getCountryCode());
        location.setPlaceId(locationDto.getPlaceId());
        location.setFormattedAddress(locationDto.getFormattedAddress());
        return location;
    }

    public LocationDto toLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setPostalCode(location.getPostalCode());
        locationDto.setCountryCode(location.getCountryCode());
        locationDto.setPlaceId(location.getPlaceId());
        locationDto.setFormattedAddress(location.getFormattedAddress());
        return locationDto;
    }
}

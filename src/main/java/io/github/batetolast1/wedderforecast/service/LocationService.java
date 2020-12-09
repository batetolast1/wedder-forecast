package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;

public interface LocationService {

    Location getLocationByPostalCodeAndCountryCode(LocationDto locationDto);

    Location getLocationByPlaceId(LocationDto locationDto);

    Location getPersistedLocation(Location location);
}

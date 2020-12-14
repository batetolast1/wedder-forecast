package io.github.batetolast1.wedderforecast.service.location;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.model.location.Location;

public interface LocationService {

    Location getLocationByPostalCodeAndCountryCode(LocationDto locationDto);

    Location getLocationByPlaceId(LocationDto locationDto);

    Location getPersistedLocation(Location location);
}

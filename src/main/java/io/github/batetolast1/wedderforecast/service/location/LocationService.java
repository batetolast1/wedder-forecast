package io.github.batetolast1.wedderforecast.service.location;

import io.github.batetolast1.wedderforecast.model.location.Location;

public interface LocationService {

    Location getPersistedLocation(Location location);
}

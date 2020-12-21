package io.github.batetolast1.wedderforecast.service.location;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;

public interface PostalCoordinateService {

    PostalCoordinate getPersistedPostalCoordinate(PostalCoordinate postalCoordinate);
}

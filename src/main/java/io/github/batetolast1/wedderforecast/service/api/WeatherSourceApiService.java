package io.github.batetolast1.wedderforecast.service.api;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;

public interface WeatherSourceApiService {

    void fetchDailyWeathers(PostalCoordinate postalCoordinate);

    void fetchHourlyWeathers(PostalCoordinate postalCoordinate);
}

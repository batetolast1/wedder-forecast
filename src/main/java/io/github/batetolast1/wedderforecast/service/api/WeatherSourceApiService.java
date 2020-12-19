package io.github.batetolast1.wedderforecast.service.api;

import io.github.batetolast1.wedderforecast.model.location.Location;

import java.time.LocalDate;

public interface WeatherSourceApiService {

    void getDailyWeathers(Location location, LocalDate localDate);

    void getHourlyWeathers(Location location, LocalDate localDate);
}

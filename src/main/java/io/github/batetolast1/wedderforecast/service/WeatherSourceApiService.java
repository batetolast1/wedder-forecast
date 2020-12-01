package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;

import java.time.LocalDate;

public interface WeatherSourceApiService {

    void getDailyWeathers(Location location, LocalDate localDate);

    void fetchHourlyWeathers(Location location, LocalDate localDate);
}

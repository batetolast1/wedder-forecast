package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;

import java.time.LocalDate;

public interface WeatherService {

    PredictedDailyWeather predictDailyWeather(Location location, LocalDate localDate);
}

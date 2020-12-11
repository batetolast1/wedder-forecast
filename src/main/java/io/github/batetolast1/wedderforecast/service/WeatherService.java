package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedHourlyWeather;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface WeatherService {

    PredictedDailyWeather predictDailyWeather(Location location, LocalDate localDate);

    PredictedHourlyWeather predictHourlyWeather(Location location, LocalDateTime localDateTime);
}

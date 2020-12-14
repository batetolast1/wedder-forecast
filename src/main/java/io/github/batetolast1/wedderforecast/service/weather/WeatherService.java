package io.github.batetolast1.wedderforecast.service.weather;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface WeatherService {

    PredictedDailyWeather predictDailyWeather(Location location, LocalDate localDate);

    PredictedHourlyWeather predictHourlyWeather(Location location, LocalDateTime localDateTime);

    Set<DailyWeather> getDailyWeathersForLocationAndLocalDate(Location location, LocalDate localDate);

    Set<HourlyWeather> getHourlyWeathersForLocationAndLocalDateTime(Location location, LocalDateTime localDateTime);
}

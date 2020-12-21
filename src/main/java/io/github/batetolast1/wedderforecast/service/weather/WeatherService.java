package io.github.batetolast1.wedderforecast.service.weather;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;

import java.time.LocalDateTime;
import java.util.Set;

public interface WeatherService {

    PredictedDailyWeather predictDailyWeather(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);

    PredictedHourlyWeather predictHourlyWeather(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);

    Set<DailyWeather> getPastDailyWeathers(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);

    Set<HourlyWeather> getPastHourlyWeathers(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);
}

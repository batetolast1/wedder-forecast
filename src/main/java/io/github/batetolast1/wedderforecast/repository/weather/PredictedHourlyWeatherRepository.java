package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictedHourlyWeatherRepository extends WeatherRepository<PredictedHourlyWeather> {
}

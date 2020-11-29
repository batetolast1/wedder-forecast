package io.github.batetolast1.wedderforecast.model.repository.weather;

import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedHourlyWeather;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictedHourlyWeatherRepository extends WeatherRepository<PredictedHourlyWeather> {
}

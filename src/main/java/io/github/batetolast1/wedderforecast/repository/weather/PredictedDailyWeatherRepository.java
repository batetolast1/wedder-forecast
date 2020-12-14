package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictedDailyWeatherRepository extends WeatherRepository<PredictedDailyWeather> {
}

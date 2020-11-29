package io.github.batetolast1.wedderforecast.model.repository.weather;

import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictedDailyWeatherRepository extends WeatherRepository<PredictedDailyWeather> {
}

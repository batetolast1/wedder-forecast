package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictedHourlyWeatherRepository extends JpaRepository<PredictedHourlyWeather, Long> {
}

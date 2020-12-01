package io.github.batetolast1.wedderforecast.model.repository.weather;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DailyWeatherRepository extends WeatherRepository<DailyWeather> {

    boolean existsByLocationAndTimestamp(Location location, LocalDateTime timestamp);

    Optional<DailyWeather> findByLocationAndTimestamp(Location location, LocalDateTime timestamp);
}

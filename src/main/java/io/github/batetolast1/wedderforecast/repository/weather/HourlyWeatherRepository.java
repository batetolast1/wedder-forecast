package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HourlyWeatherRepository extends WeatherRepository<HourlyWeather> {

    boolean existsByLocation(Location location);

    boolean existsByLocationAndTimestamp(Location location, LocalDateTime timestamp);

    Optional<HourlyWeather> findByLocationAndTimestamp(Location location, LocalDateTime timestamp);

    @Query(value = "SELECT * FROM weathers w WHERE DATE_FORMAT(w.timestamp, '%m %d %H') = DATE_FORMAT(:timestamp , '%m %d %H') AND w.location_id = :locationId AND w.weather_type = 'HourlyWeather'", nativeQuery = true)
    Set<HourlyWeather> findAllByLocationAndTimestamp(@Param(value = "locationId") Long id, @Param(value = "timestamp")LocalDateTime timestamp);
}

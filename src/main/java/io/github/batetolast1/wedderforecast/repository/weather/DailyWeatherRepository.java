package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DailyWeatherRepository extends WeatherRepository<DailyWeather> {

    boolean existsByLocationAndTimestamp(Location location, LocalDateTime timestamp);

    boolean existsByLocation(Location location);

    Optional<DailyWeather> findByLocationAndTimestamp(Location location, LocalDateTime timestamp);

    @Query(value = "SELECT * FROM weathers w WHERE DATE_FORMAT(w.timestamp, '%m %d') = DATE_FORMAT(:timestamp , '%m %d') AND w.location_id = :locationId AND w.weather_type = 'DailyWeather'", nativeQuery = true)
    Set<DailyWeather> findAllByLocationAndTimestamp(@Param(value = "locationId") Long locationId, @Param(value = "timestamp") LocalDateTime timestamp);
}

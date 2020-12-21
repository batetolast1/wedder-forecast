package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Long> {

    boolean existsByPostalCoordinate(PostalCoordinate postalCoordinate);

    Optional<DailyWeather> findByPostalCoordinateAndLocalDateTime(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);
}

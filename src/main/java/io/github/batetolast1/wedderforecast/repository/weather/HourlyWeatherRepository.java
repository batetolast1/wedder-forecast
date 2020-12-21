package io.github.batetolast1.wedderforecast.repository.weather;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface HourlyWeatherRepository extends JpaRepository<HourlyWeather, Long> {

    boolean existsByPostalCoordinate(PostalCoordinate postalCoordinate);

    Optional<HourlyWeather> findByPostalCoordinateAndLocalDateTime(PostalCoordinate postalCoordinate, LocalDateTime localDateTime);
}

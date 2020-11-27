package io.github.batetolast1.wedderforecast.model.repository.weather;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyWeatherRepository extends JpaRepository<HourlyWeather, Long> {

    boolean existsByLocation(Location location);
}

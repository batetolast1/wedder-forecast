package io.github.batetolast1.wedderforecast.model.repository.weather;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyWeatherRepository extends WeatherRepository<HourlyWeather> {

    boolean existsByLocation(Location location);
}

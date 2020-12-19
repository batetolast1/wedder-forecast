package io.github.batetolast1.wedderforecast.service.result.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.HourlyResult;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.repository.results.HourlyResultRepository;
import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.repository.weather.HourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import io.github.batetolast1.wedderforecast.service.api.WeatherSourceApiService;
import io.github.batetolast1.wedderforecast.service.result.HourlyResultService;
import io.github.batetolast1.wedderforecast.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class DefaultHourlyResultService implements HourlyResultService {

    private final LocationService locationService;
    private final WeatherService weatherService;
    private final WeatherSourceApiService weatherSourceApiService;

    private final HourlyResultRepository hourlyResultRepository;
    private final HourlyWeatherRepository hourlyWeatherRepository;
    private final UserRepository userRepository;

    public HourlyResult getHourlyResult(Location location, LocalDateTime localDateTime) {
        Location persistedLocation = locationService.getPersistedLocation(location);
        User user = userRepository.getByUsername(SecurityUtils.getUsername());

        Optional<HourlyResult> optionalHourlyResult = hourlyResultRepository.findByUserAndLocationAndLocalDateTime(user, persistedLocation, localDateTime);
        if (optionalHourlyResult.isPresent()) {
            return optionalHourlyResult.get();
        }

        weatherSourceApiService.getHourlyWeathers(persistedLocation, localDateTime.toLocalDate());

        HourlyResult hourlyResult = new HourlyResult();
        hourlyResult.setLocation(persistedLocation);
        hourlyResult.setUser(user);
        hourlyResult.setLocalDateTime(localDateTime);
        hourlyResult.setHourlyWeathers(weatherService.getHourlyWeathersForLocationAndLocalDateTime(location, localDateTime));
        hourlyResult.setPredictedHourlyWeather(weatherService.predictHourlyWeather(persistedLocation, localDateTime));
        return hourlyResultRepository.save(hourlyResult);
    }

    public HourlyResult getHourlyResult(Long id) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        Optional<HourlyResult> optionalDailyResult = hourlyResultRepository.findByIdAndUser(id, user);
        return optionalDailyResult.orElse(null);
    }

    public List<HourlyResult> getLatestHourlyResults() {
        return hourlyResultRepository.findTop3ByUserOrderByCreatedOnDesc(userRepository.getByUsername(SecurityUtils.getUsername()))
                .stream()
                .sorted(Comparator
                        .comparing(HourlyResult::getLocation, Comparator.comparing(Location::getPlaceId))
                        .thenComparing(HourlyResult::getLocalDateTime))
                .collect(Collectors.toList());
    }

    public List<HourlyResult> getAllHourlyResults() {
        return hourlyResultRepository.findAllByUser(userRepository.getByUsername(SecurityUtils.getUsername()))
                .stream()
                .sorted(Comparator
                        .comparing(HourlyResult::getLocation, Comparator.comparing(Location::getPlaceId))
                        .thenComparing(HourlyResult::getLocalDateTime))
                .collect(Collectors.toList());
    }
}

package io.github.batetolast1.wedderforecast.service.result.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.repository.results.DailyResultRepository;
import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherSourceApiService;
import io.github.batetolast1.wedderforecast.service.result.DailyResultService;
import io.github.batetolast1.wedderforecast.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Log4j2
@RequiredArgsConstructor
public class DefaultDailyResultService implements DailyResultService {

    private final LocationService locationService;
    private final WeatherService weatherService;
    private final WeatherSourceApiService weatherSourceApiService;

    private final DailyResultRepository dailyResultRepository;
    private final DailyWeatherRepository dailyWeatherRepository;
    private final UserRepository userRepository;

    public DailyResult getDailyResult(Location location, LocalDateTime localDateTime) {
        Location persistedLocation = locationService.getPersistedLocation(location);
        User user = userRepository.getByUsername(SecurityUtils.getUsername());

        Optional<DailyResult> optionalDailyResult = dailyResultRepository.findByUserAndLocationAndLocalDateTime(user, persistedLocation, localDateTime);
        if (optionalDailyResult.isPresent()) {
            return optionalDailyResult.get();
        }

        weatherSourceApiService.getDailyWeathers(persistedLocation, localDateTime.toLocalDate());

        DailyResult dailyResult = new DailyResult();
        dailyResult.setLocation(persistedLocation);
        dailyResult.setUser(user);
        dailyResult.setLocalDateTime(localDateTime);
        dailyResult.setDailyWeathers(weatherService.getDailyWeathersForLocationAndLocalDate(location, localDateTime.toLocalDate()));
        dailyResult.setPredictedDailyWeather(weatherService.predictDailyWeather(persistedLocation, localDateTime.toLocalDate()));
        return dailyResultRepository.save(dailyResult);
    }

    public DailyResult getDailyResult(Long id) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        Optional<DailyResult> optionalDailyResult = dailyResultRepository.findByIdAndUser(id, user);
        return optionalDailyResult.orElse(null);
    }

    public List<DailyResult> getLatestDailyResults() {
        return dailyResultRepository.findTop3ByUserOrderByCreatedOnDesc(userRepository.getByUsername(SecurityUtils.getUsername()))
                .stream()
                .sorted(Comparator
                        .comparing(DailyResult::getLocation, Comparator.comparing(Location::getPlaceId))
                        .thenComparing(DailyResult::getLocalDateTime))
                .collect(Collectors.toList());
    }

    public List<DailyResult> getAllDailyResults() {
        return dailyResultRepository.findAllByUser(userRepository.getByUsername(SecurityUtils.getUsername()))
                .stream()
                .sorted(Comparator
                        .comparing(DailyResult::getLocation, Comparator.comparing(Location::getPlaceId))
                        .thenComparing(DailyResult::getLocalDateTime))
                .collect(Collectors.toList());
    }
}

package io.github.batetolast1.wedderforecast.service.result.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.HourlyResult;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import io.github.batetolast1.wedderforecast.repository.results.HourlyResultRepository;
import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.service.result.HourlyResultService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import io.github.batetolast1.wedderforecast.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional

@RequiredArgsConstructor
public class DefaultHourlyResultService implements HourlyResultService {

    private final WeatherService weatherService;

    private final HourlyResultRepository hourlyResultRepository;
    private final UserRepository userRepository;

    public HourlyResult getHourlyResult(Location location, LocalDateTime localDateTime) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        return hourlyResultRepository.findByUserAndLocationAndLocalDateTime(user, location, localDateTime)
                .orElse(getNewHourlyResult(user, location, localDateTime));
    }

    private HourlyResult getNewHourlyResult(User user, Location location, LocalDateTime localDateTime) {
        Set<HourlyWeather> hourlyWeathers = weatherService.getPastHourlyWeathers(location.getPostalCoordinate(), localDateTime);
        PredictedHourlyWeather predictedHourlyWeather = weatherService.predictHourlyWeather(location.getPostalCoordinate(), localDateTime);

        HourlyResult hourlyResult = new HourlyResult();
        hourlyResult.setLocation(location);
        hourlyResult.setUser(user);
        hourlyResult.setLocalDateTime(localDateTime);
        hourlyResult.setHourlyWeathers(hourlyWeathers);
        hourlyResult.setPredictedHourlyWeather(predictedHourlyWeather);
        return hourlyResultRepository.save(hourlyResult);
    }

    public HourlyResult getHourlyResult(Long id) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        return hourlyResultRepository.findByIdAndUser(id, user)
                .orElse(null);
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

package io.github.batetolast1.wedderforecast.service.result.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.repository.results.DailyResultRepository;
import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.service.result.DailyResultService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import io.github.batetolast1.wedderforecast.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional

@Log4j2
@RequiredArgsConstructor
public class DefaultDailyResultService implements DailyResultService {

    private final WeatherService weatherService;

    private final DailyResultRepository dailyResultRepository;
    private final UserRepository userRepository;

    public DailyResult getDailyResult(Location location, LocalDateTime localDateTime) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        return dailyResultRepository.findByUserAndLocationAndLocalDateTime(user, location, localDateTime)
                .orElse(getNewDailyResult(user, location, localDateTime));
    }

    private DailyResult getNewDailyResult(User user, Location location, LocalDateTime localDateTime) {
        Set<DailyWeather> dailyWeathers = weatherService.getPastDailyWeathers(location.getPostalCoordinate(), localDateTime);
        PredictedDailyWeather predictedDailyWeather = weatherService.predictDailyWeather(location.getPostalCoordinate(), localDateTime);

        DailyResult dailyResult = new DailyResult();
        dailyResult.setLocation(location);
        dailyResult.setUser(user);
        dailyResult.setLocalDateTime(localDateTime);
        dailyResult.setDailyWeathers(dailyWeathers);
        dailyResult.setPredictedDailyWeather(predictedDailyWeather);
        return dailyResultRepository.save(dailyResult);
    }

    public DailyResult getDailyResult(Long id) {
        User user = userRepository.getByUsername(SecurityUtils.getUsername());
        return dailyResultRepository.findByIdAndUser(id, user)
                .orElse(null);
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

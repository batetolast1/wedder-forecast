package io.github.batetolast1.wedderforecast.service.result.impl;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.SimpleDailyResult;
import io.github.batetolast1.wedderforecast.repository.results.SimpleDailyResultRepository;
import io.github.batetolast1.wedderforecast.service.result.SimpleDailyResultService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional

@RequiredArgsConstructor
public class DefaultSimpleDailyResultService implements SimpleDailyResultService {

    private final WeatherService weatherService;

    private final SimpleDailyResultRepository simpleDailyResultRepository;

    public SimpleDailyResult getSimpleDailyResult(Location location, LocalDateTime localDateTime) {
        Optional<SimpleDailyResult> optionalSimpleDailyResult = simpleDailyResultRepository.findByLocationAndLocalDateTime(location, localDateTime);
        if (optionalSimpleDailyResult.isPresent()) {
            return optionalSimpleDailyResult.get();
        }

        SimpleDailyResult simpleDailyResult = new SimpleDailyResult();
        simpleDailyResult.setLocation(location);
        simpleDailyResult.setLocalDateTime(localDateTime);
        simpleDailyResult.setPredictedDailyWeather(weatherService.predictDailyWeather(location.getPostalCoordinate(), localDateTime));
        return simpleDailyResultRepository.save(simpleDailyResult);
    }

    public SimpleDailyResult getSimpleDailyResult(Long id) {
        return simpleDailyResultRepository.findById(id)
                .orElse(null);
    }
}

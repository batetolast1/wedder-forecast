package io.github.batetolast1.wedderforecast.dto.mapper.result;

import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.PredictedDailyWeatherMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.SimpleDailyResultDto;
import io.github.batetolast1.wedderforecast.model.results.SimpleDailyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class SimpleDailyResultMapper {

    private final LocationMapper locationMapper;
    private final PredictedDailyWeatherMapper predictedDailyWeatherMapper;

    public SimpleDailyResultDto toSimpleDailyResultDto(SimpleDailyResult simpleDailyResult) {
        SimpleDailyResultDto simpleDailyResultDto = new SimpleDailyResultDto();
        simpleDailyResultDto.setId(simpleDailyResult.getId());
        simpleDailyResultDto.setLocalDateTime(simpleDailyResult.getLocalDateTime());
        simpleDailyResultDto.setLocationDto(locationMapper.toLocationDto(simpleDailyResult.getLocation()));
        simpleDailyResultDto.setSimplePredictedDailyWeatherDto(predictedDailyWeatherMapper.toSimplePredictedDailyWeatherDto(simpleDailyResult.getPredictedDailyWeather()));
        return simpleDailyResultDto;
    }
}

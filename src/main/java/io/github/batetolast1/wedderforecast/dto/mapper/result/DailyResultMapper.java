package io.github.batetolast1.wedderforecast.dto.mapper.result;

import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.DailyWeatherMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.PredictedDailyWeatherMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.DailyResultDto;
import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service

@Log4j2
@RequiredArgsConstructor
public class DailyResultMapper {

    private final LocationMapper locationMapper;
    private final DailyWeatherMapper dailyWeatherMapper;
    private final PredictedDailyWeatherMapper predictedDailyWeatherMapper;

    public DailyResultDto toDailyResultDto(DailyResult dailyResult) {
        DailyResultDto dailyResultDto = new DailyResultDto();
        dailyResultDto.setId(dailyResult.getId());
        dailyResultDto.setLocalDateTime(dailyResult.getLocalDateTime());
        dailyResultDto.setLocationDto(locationMapper.toLocationDto(dailyResult.getLocation()));
        dailyResultDto.setDailyWeatherDtos(
                dailyResult.getDailyWeathers()
                        .stream()
                        .sorted(Comparator.comparing(DailyWeather::getTimestamp).reversed())
                        .map(dailyWeatherMapper::toDailyWeatherDto)
                        .collect(Collectors.toList()));
        dailyResultDto.setPredictedDailyWeatherDto(predictedDailyWeatherMapper.toPredictedDailyWeatherDto(dailyResult.getPredictedDailyWeather()));
        return dailyResultDto;
    }
}

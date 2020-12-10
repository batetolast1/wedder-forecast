package io.github.batetolast1.wedderforecast.dto.mapper.result;

import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.PredictedDailyWeatherMapper;
import io.github.batetolast1.wedderforecast.model.entity.results.SimpleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class SimpleResultMapper {

    private final LocationMapper locationMapper;
    private final PredictedDailyWeatherMapper predictedDailyWeatherMapper;

    public ResponseSimpleResultDto toResponseSimpleResultDto(SimpleResult simpleResult) {
        ResponseSimpleResultDto responseSimpleResultDto = new ResponseSimpleResultDto();
        responseSimpleResultDto.setLocalDateTime(simpleResult.getLocalDateTime());
        responseSimpleResultDto.setLocationDto(locationMapper.toLocationDto(simpleResult.getLocation()));
        responseSimpleResultDto.setPredictedDailyWeatherDto(predictedDailyWeatherMapper.toPredictedDailyWeatherDto(simpleResult.getPredictedDailyWeather()));
        return responseSimpleResultDto;
    }
}

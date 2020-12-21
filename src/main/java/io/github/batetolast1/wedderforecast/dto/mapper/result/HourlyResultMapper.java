package io.github.batetolast1.wedderforecast.dto.mapper.result;

import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.HourlyWeatherMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.weather.PredictedHourlyWeatherMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.HourlyResultDto;
import io.github.batetolast1.wedderforecast.model.results.HourlyResult;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service

@Log4j2
@RequiredArgsConstructor
public class HourlyResultMapper {

    private final LocationMapper locationMapper;
    private final HourlyWeatherMapper hourlyWeatherMapper;
    private final PredictedHourlyWeatherMapper predictedHourlyWeatherMapper;

    public HourlyResultDto toHourlyResultDto(HourlyResult hourlyResult) {
        HourlyResultDto hourlyResultDto = new HourlyResultDto();
        hourlyResultDto.setId(hourlyResult.getId());
        hourlyResultDto.setLocalDateTime(hourlyResult.getLocalDateTime());
        hourlyResultDto.setLocationDto(locationMapper.toLocationDto(hourlyResult.getLocation()));
        hourlyResultDto.setHourlyWeatherDtos(
                hourlyResult.getHourlyWeathers()
                        .stream()
                        .sorted(Comparator.comparing(HourlyWeather::getLocalDateTime).reversed())
                        .map(hourlyWeatherMapper::toHourlyWeatherDto)
                        .collect(Collectors.toList()));
        hourlyResultDto.setPredictedHourlyWeatherDto(predictedHourlyWeatherMapper.toPredictedHourlyWeatherDto(hourlyResult.getPredictedHourlyWeather()));
        return hourlyResultDto;
    }
}

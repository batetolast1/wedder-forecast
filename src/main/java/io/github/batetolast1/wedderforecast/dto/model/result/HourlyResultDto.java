package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.HourlyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedHourlyWeatherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class HourlyResultDto {

    private Long id;
    private LocalDateTime localDateTime;
    private LocationDto locationDto;
    private List<HourlyWeatherDto> hourlyWeatherDtos;
    private PredictedHourlyWeatherDto predictedHourlyWeatherDto;
}

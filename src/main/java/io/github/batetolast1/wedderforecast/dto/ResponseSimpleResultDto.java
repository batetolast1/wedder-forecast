package io.github.batetolast1.wedderforecast.dto;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedDailyWeatherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ResponseSimpleResultDto {

    private LocalDateTime localDateTime;
    private LocationDto locationDto;
    private PredictedDailyWeatherDto predictedDailyWeatherDto;
}

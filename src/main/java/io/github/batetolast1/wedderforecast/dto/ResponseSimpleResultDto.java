package io.github.batetolast1.wedderforecast.dto;

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

package io.github.batetolast1.wedderforecast.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ResponseSimpleResultDto {

    private LocalDate localDate;
    private LocationDto locationDto;
    private PredictedDailyWeatherDto predictedDailyWeatherDto;
}

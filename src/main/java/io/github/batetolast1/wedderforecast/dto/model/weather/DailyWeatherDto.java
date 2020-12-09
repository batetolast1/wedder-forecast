package io.github.batetolast1.wedderforecast.dto.model.weather;

import io.github.batetolast1.wedderforecast.dto.SystemRatingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class DailyWeatherDto {

    private SystemRatingDto systemRatingDto;

    private LocalDateTime timestamp;

    private Double tempAvg;
    private Double feelsLikeAvg;
    private Double heatIndexAvg;
    private Double mslPresAvg;
    private Double precip;
    private Double snowfall;
    private Double cldCvrAvg;
    private Double windSpdAvg;
    private Double relHumAvg;
}

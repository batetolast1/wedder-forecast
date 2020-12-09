package io.github.batetolast1.wedderforecast.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PredictedDailyWeatherDto {

    private LocalDateTime timestamp;

    private SystemRatingDto systemRatingDto;

    private Double tempAvg;
    private Double feelsLikeAvg;
    private Double heatIndexAvg;
    private Double mslPresAvg;
    private Double precip;
    private Double snowfall;
    private Double cldCvrAvg;
    private Double windSpdAvg;
    private Double relHumAvg;

    private Double tempAvgDeviation;
    private Double feelsLikeAvgDeviation;
    private Double heatIndexAvgDeviation;
    private Double mslPresAvgDeviation;
    private Double precipDeviation;
    private Double snowfallDeviation;
    private Double cldCvrAvgDeviation;
    private Double windSpdAvgDeviation;
    private Double relHumAvgDeviation;
}

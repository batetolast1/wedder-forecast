package io.github.batetolast1.wedderforecast.dto.model.weather;

import io.github.batetolast1.wedderforecast.dto.model.rating.SystemRatingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PredictedHourlyWeatherDto {

    private LocalDateTime timestamp;

    private SystemRatingDto systemRatingDto;

    private Double temp;
    private Double feelsLike;
    private Double heatIndex;
    private Double mslPres;
    private Double precip;
    private Double snowfall;
    private Double cldCvr;
    private Double windSpd;
    private Double relHum;

    private Double tempDeviation;
    private Double feelsLikeDeviation;
    private Double heatIndexDeviation;
    private Double mslPresDeviation;
    private Double precipDeviation;
    private Double snowfallDeviation;
    private Double cldCvrDeviation;
    private Double windSpdDeviation;
    private Double relHumDeviation;
}

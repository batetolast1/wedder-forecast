package io.github.batetolast1.wedderforecast.dto.model.weather;

import io.github.batetolast1.wedderforecast.dto.model.rating.SystemRatingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class HourlyWeatherDto {

    private SystemRatingDto systemRatingDto;

    private LocalDateTime timestamp;

    private Double temp;
    private Double feelsLike;
    private Double heatIndex;
    private Double mslPres;
    private Double precip;
    private Double snowfall;
    private Double cldCvr;
    private Double windSpd;
    private Double relHum;
}

package io.github.batetolast1.wedderforecast.dto.model.weather;

import io.github.batetolast1.wedderforecast.dto.model.rating.SimpleSystemRatingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SimplePredictedDailyWeatherDto {

    private LocalDateTime localDateTime;

    private SimpleSystemRatingDto simpleSystemRatingDto;

    private Double tempAvg;
    private Double precip;
    private Double cldCvrAvg;

    private Double tempAvgDeviation;
    private Double precipDeviation;
    private Double cldCvrAvgDeviation;
}

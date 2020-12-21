package io.github.batetolast1.wedderforecast.dto.model.weather;

import io.github.batetolast1.wedderforecast.dto.model.rating.SimpleSystemRatingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SimplePredictedHourlyWeatherDto {

    private LocalDateTime localDateTime;

    private SimpleSystemRatingDto simpleSystemRatingDto;

    private Double temp;
    private Double precip;
    private Double cldCvr;
}

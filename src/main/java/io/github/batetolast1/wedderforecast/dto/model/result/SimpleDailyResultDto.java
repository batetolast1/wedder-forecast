package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.SimplePredictedDailyWeatherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SimpleDailyResultDto {

    private Long id;
    private LocalDateTime localDateTime;
    private LocationDto locationDto;
    private SimplePredictedDailyWeatherDto simplePredictedDailyWeatherDto;
}

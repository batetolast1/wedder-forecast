package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedDailyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.DailyWeatherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DailyResultDto {

    private Long id;
    private LocalDateTime localDateTime;
    private LocationDto locationDto;
    private List<DailyWeatherDto> dailyWeatherDtos;
    private PredictedDailyWeatherDto predictedDailyWeatherDto;
}

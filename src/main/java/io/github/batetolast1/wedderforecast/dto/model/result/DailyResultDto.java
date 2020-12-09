package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.dto.PredictedDailyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.DailyWeatherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DailyResultDto {

    private Long id;
    private LocalDate localDate;
    private LocationDto locationDto;
    private List<DailyWeatherDto> dailyWeatherDtos;
    private PredictedDailyWeatherDto predictedDailyWeatherDto;
}

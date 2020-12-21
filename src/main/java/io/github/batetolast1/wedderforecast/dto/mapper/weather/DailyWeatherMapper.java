package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.DailyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.batetolast1.wedderforecast.util.MathUtils.*;

@Service

@RequiredArgsConstructor
public class DailyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public DailyWeatherDto toDailyWeatherDto(DailyWeather dailyWeather) {
        DailyWeatherDto dailyWeatherDto = new DailyWeatherDto();

        dailyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(dailyWeather.getSystemRating()));
        dailyWeatherDto.setLocalDateTime(dailyWeather.getLocalDateTime());

        dailyWeatherDto.setTempAvg(round(convertToCelsius(dailyWeather.getTempAvg()), 1));
        dailyWeatherDto.setFeelsLikeAvg(round(convertToCelsius(dailyWeather.getFeelsLikeAvg()), 1));
        dailyWeatherDto.setMslPresAvg(round(dailyWeather.getMslPresAvg(), 1));
        dailyWeatherDto.setPrecip(round(convertToMm(dailyWeather.getPrecip()), 1));
        dailyWeatherDto.setSnowfall(round(convertToMm(dailyWeather.getSnowfall()), 1));
        dailyWeatherDto.setCldCvrAvg(round(dailyWeather.getCldCvrAvg(), 1));
        dailyWeatherDto.setWindSpdAvg(round(convertToKmh(dailyWeather.getWindSpdAvg()), 1));
        dailyWeatherDto.setRelHumAvg(round(dailyWeather.getRelHumAvg(), 1));
        return dailyWeatherDto;
    }
}

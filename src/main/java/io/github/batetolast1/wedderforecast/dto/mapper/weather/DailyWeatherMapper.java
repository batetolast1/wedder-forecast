package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.DailyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class DailyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public DailyWeatherDto toDailyWeatherDto(DailyWeather dailyWeather) {
        DailyWeatherDto dailyWeatherDto = new DailyWeatherDto();

        dailyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(dailyWeather.getSystemRating()));
        dailyWeatherDto.setTimestamp(dailyWeather.getTimestamp());

        dailyWeatherDto.setTempAvg(dailyWeather.getTempAvg());
        dailyWeatherDto.setFeelsLikeAvg(dailyWeather.getFeelsLikeAvg());
        dailyWeatherDto.setHeatIndexAvg(dailyWeather.getHeatIndexAvg());
        dailyWeatherDto.setMslPresAvg(dailyWeather.getMslPresAvg());
        dailyWeatherDto.setPrecip(dailyWeather.getPrecip());
        dailyWeatherDto.setSnowfall(dailyWeather.getSnowfall());
        dailyWeatherDto.setCldCvrAvg(dailyWeather.getCldCvrAvg());
        dailyWeatherDto.setWindSpdAvg(dailyWeather.getWindSpdAvg());
        dailyWeatherDto.setRelHumAvg(dailyWeather.getRelHumAvg());
        return dailyWeatherDto;
    }
}

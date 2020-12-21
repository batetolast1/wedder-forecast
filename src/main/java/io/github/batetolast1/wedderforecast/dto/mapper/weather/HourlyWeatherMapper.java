package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.HourlyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.batetolast1.wedderforecast.util.MathUtils.*;
import static io.github.batetolast1.wedderforecast.util.MathUtils.round;

@Service

@RequiredArgsConstructor
public class HourlyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public HourlyWeatherDto toHourlyWeatherDto(HourlyWeather hourlyWeather) {
        HourlyWeatherDto hourlyWeatherDto = new HourlyWeatherDto();

        hourlyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(hourlyWeather.getSystemRating()));
        hourlyWeatherDto.setLocalDateTime(hourlyWeather.getLocalDateTime());

        hourlyWeatherDto.setTemp(round(convertToCelsius(hourlyWeather.getTemp()), 1));
        hourlyWeatherDto.setFeelsLike(round(convertToCelsius(hourlyWeather.getFeelsLike()), 1));
        hourlyWeatherDto.setMslPres(round(hourlyWeather.getMslPres(), 1));
        hourlyWeatherDto.setPrecip(round(convertToMm(hourlyWeather.getPrecip()), 1));
        hourlyWeatherDto.setSnowfall(round(convertToMm(hourlyWeather.getSnowfall()), 1));
        hourlyWeatherDto.setCldCvr(round(hourlyWeather.getCldCvr(), 1));
        hourlyWeatherDto.setWindSpd(round(convertToKmh(hourlyWeather.getWindSpd()), 1));
        hourlyWeatherDto.setRelHum(round(hourlyWeather.getRelHum(), 1));
        return hourlyWeatherDto;
    }
}

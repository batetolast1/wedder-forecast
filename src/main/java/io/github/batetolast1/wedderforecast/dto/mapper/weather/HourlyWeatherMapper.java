package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.HourlyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class HourlyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public HourlyWeatherDto toHourlyWeatherDto(HourlyWeather hourlyWeather) {
        HourlyWeatherDto hourlyWeatherDto = new HourlyWeatherDto();

        hourlyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(hourlyWeather.getSystemRating()));
        hourlyWeatherDto.setTimestamp(hourlyWeather.getTimestamp());

        hourlyWeatherDto.setTemp(hourlyWeather.getTemp());
        hourlyWeatherDto.setFeelsLike(hourlyWeather.getFeelsLike());
        hourlyWeatherDto.setHeatIndex(hourlyWeather.getHeatIndex());
        hourlyWeatherDto.setMslPres(hourlyWeather.getMslPres());
        hourlyWeatherDto.setPrecip(hourlyWeather.getPrecip());
        hourlyWeatherDto.setSnowfall(hourlyWeather.getSnowfall());
        hourlyWeatherDto.setCldCvr(hourlyWeather.getCldCvr());
        hourlyWeatherDto.setWindSpd(hourlyWeather.getWindSpd());
        hourlyWeatherDto.setRelHum(hourlyWeather.getRelHum());
        return hourlyWeatherDto;
    }
}

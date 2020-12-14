package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedHourlyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class PredictedHourlyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public PredictedHourlyWeatherDto toPredictedHourlyWeatherDto(PredictedHourlyWeather predictedHourlyWeather) {
        PredictedHourlyWeatherDto predictedHourlyWeatherDto = new PredictedHourlyWeatherDto();

        predictedHourlyWeatherDto.setTimestamp(predictedHourlyWeather.getTimestamp());
        predictedHourlyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(predictedHourlyWeather.getSystemRating()));

        predictedHourlyWeatherDto.setTemp(predictedHourlyWeather.getTemp());
        predictedHourlyWeatherDto.setFeelsLike(predictedHourlyWeather.getFeelsLike());
        predictedHourlyWeatherDto.setHeatIndex(predictedHourlyWeather.getHeatIndex());
        predictedHourlyWeatherDto.setMslPres(predictedHourlyWeather.getMslPres());
        predictedHourlyWeatherDto.setPrecip(predictedHourlyWeather.getPrecip());
        predictedHourlyWeatherDto.setSnowfall(predictedHourlyWeather.getSnowfall());
        predictedHourlyWeatherDto.setCldCvr(predictedHourlyWeather.getCldCvr());
        predictedHourlyWeatherDto.setWindSpd(predictedHourlyWeather.getWindSpd());
        predictedHourlyWeatherDto.setRelHum(predictedHourlyWeather.getRelHum());

        predictedHourlyWeatherDto.setTempDeviation(predictedHourlyWeather.getTempDeviation());
        predictedHourlyWeatherDto.setFeelsLikeDeviation(predictedHourlyWeather.getFeelsLikeDeviation());
        predictedHourlyWeatherDto.setHeatIndexDeviation(predictedHourlyWeather.getHeatIndexDeviation());
        predictedHourlyWeatherDto.setMslPresDeviation(predictedHourlyWeather.getMslPresDeviation());
        predictedHourlyWeatherDto.setPrecipDeviation(predictedHourlyWeather.getPrecipDeviation());
        predictedHourlyWeatherDto.setSnowfallDeviation(predictedHourlyWeather.getSnowfallDeviation());
        predictedHourlyWeatherDto.setCldCvrDeviation(predictedHourlyWeather.getCldCvrDeviation());
        predictedHourlyWeatherDto.setWindSpdDeviation(predictedHourlyWeather.getWindSpdDeviation());
        predictedHourlyWeatherDto.setRelHumDeviation(predictedHourlyWeather.getRelHumDeviation());
        return predictedHourlyWeatherDto;
    }
}

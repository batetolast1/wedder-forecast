package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedHourlyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.model.weather.SimplePredictedHourlyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.batetolast1.wedderforecast.util.MathUtils.*;

@Service

@RequiredArgsConstructor
public class PredictedHourlyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public SimplePredictedHourlyWeatherDto toSimplePredictedHourlyWeatherDto(PredictedHourlyWeather predictedHourlyWeather) {
        SimplePredictedHourlyWeatherDto simplePredictedHourlyWeatherDto = new SimplePredictedHourlyWeatherDto();

        simplePredictedHourlyWeatherDto.setLocalDateTime(predictedHourlyWeather.getLocalDateTime());

        simplePredictedHourlyWeatherDto.setSimpleSystemRatingDto(systemRatingMapper.toSimpleSystemRatingDto(predictedHourlyWeather.getSystemRating()));

        simplePredictedHourlyWeatherDto.setTemp(round(convertToCelsius(predictedHourlyWeather.getTemp()), 1));
        simplePredictedHourlyWeatherDto.setPrecip(round(convertToMm(predictedHourlyWeather.getPrecip()), 1));
        simplePredictedHourlyWeatherDto.setCldCvr(round(predictedHourlyWeather.getCldCvr(), 1));

        return simplePredictedHourlyWeatherDto;
    }

    public PredictedHourlyWeatherDto toPredictedHourlyWeatherDto(PredictedHourlyWeather predictedHourlyWeather) {
        PredictedHourlyWeatherDto predictedHourlyWeatherDto = new PredictedHourlyWeatherDto();

        predictedHourlyWeatherDto.setLocalDateTime(predictedHourlyWeather.getLocalDateTime());
        predictedHourlyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(predictedHourlyWeather.getSystemRating()));

        predictedHourlyWeatherDto.setTemp(round(convertToCelsius(predictedHourlyWeather.getTemp()), 1));
        predictedHourlyWeatherDto.setFeelsLike(round(convertToCelsius(predictedHourlyWeather.getFeelsLike()), 1));
        predictedHourlyWeatherDto.setMslPres(round(predictedHourlyWeather.getMslPres(), 1));
        predictedHourlyWeatherDto.setPrecip(round(convertToMm(predictedHourlyWeather.getPrecip()), 1));
        predictedHourlyWeatherDto.setSnowfall(round(convertToMm(predictedHourlyWeather.getSnowfall()), 1));
        predictedHourlyWeatherDto.setCldCvr(round(predictedHourlyWeather.getCldCvr(), 1));
        predictedHourlyWeatherDto.setWindSpd(round(convertToKmh(predictedHourlyWeather.getWindSpd()), 1));
        predictedHourlyWeatherDto.setRelHum(round(predictedHourlyWeather.getRelHum(), 1));

        predictedHourlyWeatherDto.setTempDeviation(round(predictedHourlyWeather.getTempDeviation() / 2, 1));
        predictedHourlyWeatherDto.setFeelsLikeDeviation(round(predictedHourlyWeather.getFeelsLikeDeviation() / 2, 1));
        predictedHourlyWeatherDto.setMslPresDeviation(round(predictedHourlyWeather.getMslPresDeviation() / 2, 1));
        predictedHourlyWeatherDto.setPrecipDeviation(round(predictedHourlyWeather.getPrecipDeviation() / 2, 1));
        predictedHourlyWeatherDto.setSnowfallDeviation(round(predictedHourlyWeather.getSnowfallDeviation() / 2, 1));
        predictedHourlyWeatherDto.setCldCvrDeviation(round(predictedHourlyWeather.getCldCvrDeviation() / 2, 1));
        predictedHourlyWeatherDto.setWindSpdDeviation(round(predictedHourlyWeather.getWindSpdDeviation() / 2, 1));
        predictedHourlyWeatherDto.setRelHumDeviation(round(predictedHourlyWeather.getRelHumDeviation() / 2, 1));
        return predictedHourlyWeatherDto;
    }
}

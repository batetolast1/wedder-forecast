package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.PredictedDailyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class PredictedDailyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public PredictedDailyWeatherDto toPredictedDailyWeatherDto(PredictedDailyWeather predictedDailyWeather) {
        PredictedDailyWeatherDto predictedDailyWeatherDto = new PredictedDailyWeatherDto();

        predictedDailyWeatherDto.setTimestamp(predictedDailyWeather.getTimestamp());
        predictedDailyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(predictedDailyWeather.getSystemRating()));

        predictedDailyWeatherDto.setTempAvg(predictedDailyWeather.getTempAvg());
        predictedDailyWeatherDto.setFeelsLikeAvg(predictedDailyWeather.getFeelsLikeAvg());
        predictedDailyWeatherDto.setHeatIndexAvg(predictedDailyWeather.getHeatIndexAvg());
        predictedDailyWeatherDto.setMslPresAvg(predictedDailyWeather.getMslPresAvg());
        predictedDailyWeatherDto.setPrecip(predictedDailyWeather.getPrecip());
        predictedDailyWeatherDto.setSnowfall(predictedDailyWeather.getSnowfall());
        predictedDailyWeatherDto.setCldCvrAvg(predictedDailyWeather.getCldCvrAvg());
        predictedDailyWeatherDto.setWindSpdAvg(predictedDailyWeather.getWindSpdAvg());
        predictedDailyWeatherDto.setRelHumAvg(predictedDailyWeather.getRelHumAvg());

        predictedDailyWeatherDto.setTempAvgDeviation(predictedDailyWeather.getTempAvgDeviation());
        predictedDailyWeatherDto.setFeelsLikeAvgDeviation(predictedDailyWeather.getFeelsLikeAvgDeviation());
        predictedDailyWeatherDto.setHeatIndexAvgDeviation(predictedDailyWeather.getHeatIndexAvgDeviation());
        predictedDailyWeatherDto.setMslPresAvgDeviation(predictedDailyWeather.getMslPresAvgDeviation());
        predictedDailyWeatherDto.setPrecipDeviation(predictedDailyWeather.getPrecipDeviation());
        predictedDailyWeatherDto.setSnowfallDeviation(predictedDailyWeather.getSnowfallDeviation());
        predictedDailyWeatherDto.setCldCvrAvgDeviation(predictedDailyWeather.getCldCvrAvgDeviation());
        predictedDailyWeatherDto.setWindSpdAvgDeviation(predictedDailyWeather.getWindSpdAvgDeviation());
        predictedDailyWeatherDto.setRelHumAvgDeviation(predictedDailyWeather.getRelHumAvgDeviation());
        return predictedDailyWeatherDto;
    }
}

package io.github.batetolast1.wedderforecast.dto.mapper.weather;

import io.github.batetolast1.wedderforecast.dto.mapper.rating.SystemRatingMapper;
import io.github.batetolast1.wedderforecast.dto.model.weather.PredictedDailyWeatherDto;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.batetolast1.wedderforecast.util.MathUtils.*;

@Service

@RequiredArgsConstructor
public class PredictedDailyWeatherMapper {

    private final SystemRatingMapper systemRatingMapper;

    public PredictedDailyWeatherDto toPredictedDailyWeatherDto(PredictedDailyWeather predictedDailyWeather) {
        PredictedDailyWeatherDto predictedDailyWeatherDto = new PredictedDailyWeatherDto();

        predictedDailyWeatherDto.setTimestamp(predictedDailyWeather.getTimestamp());
        predictedDailyWeatherDto.setSystemRatingDto(systemRatingMapper.toSystemRatingDto(predictedDailyWeather.getSystemRating()));

        predictedDailyWeatherDto.setTempAvg(round(convertToCelsius(predictedDailyWeather.getTempAvg()), 1));
        predictedDailyWeatherDto.setFeelsLikeAvg(round(convertToCelsius(predictedDailyWeather.getFeelsLikeAvg()), 1));
        predictedDailyWeatherDto.setHeatIndexAvg(round(convertToCelsius(predictedDailyWeather.getHeatIndexAvg()), 1));
        predictedDailyWeatherDto.setMslPresAvg(round(predictedDailyWeather.getMslPresAvg(), 1));
        predictedDailyWeatherDto.setPrecip(round(convertToMm(predictedDailyWeather.getPrecip()), 1));
        predictedDailyWeatherDto.setSnowfall(round(convertToMm(predictedDailyWeather.getSnowfall()), 1));
        predictedDailyWeatherDto.setCldCvrAvg(round(predictedDailyWeather.getCldCvrAvg(), 1));
        predictedDailyWeatherDto.setWindSpdAvg(round(convertToKmh(predictedDailyWeather.getWindSpdAvg()), 1));
        predictedDailyWeatherDto.setRelHumAvg(round(predictedDailyWeather.getRelHumAvg(), 1));

        predictedDailyWeatherDto.setTempAvgDeviation(round(predictedDailyWeather.getTempAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setFeelsLikeAvgDeviation(round(predictedDailyWeather.getFeelsLikeAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setHeatIndexAvgDeviation(round(predictedDailyWeather.getHeatIndexAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setMslPresAvgDeviation(round(predictedDailyWeather.getMslPresAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setPrecipDeviation(round(predictedDailyWeather.getPrecipDeviation() / 2, 1));
        predictedDailyWeatherDto.setSnowfallDeviation(round(predictedDailyWeather.getSnowfallDeviation() / 2, 1));
        predictedDailyWeatherDto.setCldCvrAvgDeviation(round(predictedDailyWeather.getCldCvrAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setWindSpdAvgDeviation(round(predictedDailyWeather.getWindSpdAvgDeviation() / 2, 1));
        predictedDailyWeatherDto.setRelHumAvgDeviation(round(predictedDailyWeather.getRelHumAvgDeviation() / 2, 1));
        return predictedDailyWeatherDto;
    }
}

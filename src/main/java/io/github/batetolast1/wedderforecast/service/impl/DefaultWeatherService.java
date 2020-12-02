package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.model.repository.weather.PredictedDailyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.RatingService;
import io.github.batetolast1.wedderforecast.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional

@Log4j2
@RequiredArgsConstructor
public class DefaultWeatherService implements WeatherService {

    private static final int YEARS_TO_CHECK = 5;

    private final RatingService ratingService;

    private final DailyWeatherRepository dailyWeatherRepository;
    private final PredictedDailyWeatherRepository predictedDailyWeatherRepository;

    @Override
    public PredictedDailyWeather predictDailyWeather(Location location, LocalDate localDate) {
        Set<DailyWeather> dailyWeathers = getDailyWeathersForLocationAndLocalDate(location, localDate);

        PredictedDailyWeather predictedDailyWeather = new PredictedDailyWeather();
        predictedDailyWeather.setLocation(location);
        predictedDailyWeather.setTimestamp(localDate.atStartOfDay());

        predictedDailyWeather.setTempAvg(calculateTempAvg(dailyWeathers));
        predictedDailyWeather.setFeelsLikeAvg(calculateFeelsLikeAvg(dailyWeathers));
        predictedDailyWeather.setHeatIndexAvg(calculateHeatIndexAvg(dailyWeathers));
        predictedDailyWeather.setMslPresAvg(calculateMslPresAvg(dailyWeathers));
        predictedDailyWeather.setPrecip(calculatePrecip(dailyWeathers));
        predictedDailyWeather.setSnowfall(calculateSnowfall(dailyWeathers));
        predictedDailyWeather.setCldCvrAvg(calculateCldCvrAvg(dailyWeathers));
        predictedDailyWeather.setWindSpdAvg(calculateWindSpdAvg(dailyWeathers));
        predictedDailyWeather.setRelHumAvg(calculateRelHumAvg(dailyWeathers));

        predictedDailyWeather.setTempAvgDeviation(calculateTempAvgDeviation(dailyWeathers));
        predictedDailyWeather.setFeelsLikeAvgDeviation(calculateFeelsLikeAvgDeviation(dailyWeathers));
        predictedDailyWeather.setHeatIndexAvgDeviation(calculateHeatIndexAvgDeviation(dailyWeathers));
        predictedDailyWeather.setMslPresAvgDeviation(calculateMslPresAvgDeviation(dailyWeathers));
        predictedDailyWeather.setPrecipDeviation(calculatePrecipDeviation(dailyWeathers));
        predictedDailyWeather.setSnowfallDeviation(calculateSnowfallDeviation(dailyWeathers));
        predictedDailyWeather.setCldCvrAvgDeviation(calculateCldCvrAvgDeviation(dailyWeathers));
        predictedDailyWeather.setWindSpdAvgDeviation(calculateWindSpdAvgDeviation(dailyWeathers));
        predictedDailyWeather.setRelHumAvgDeviation(calculateRelHumAvgDeviation(dailyWeathers));

        predictedDailyWeather.setSystemRating(ratingService.rateDailyWeather(predictedDailyWeather));

        return predictedDailyWeatherRepository.save(predictedDailyWeather);
    }

    private Set<DailyWeather> getDailyWeathersForLocationAndLocalDate(Location location, LocalDate localDate) {
        Set<DailyWeather> dailyWeathers = new HashSet<>();
        for (int i = 1; i <= YEARS_TO_CHECK; i++) {
            Optional<DailyWeather> optionalDailyWeather = dailyWeatherRepository.findByLocationAndTimestamp(location, localDate.minus(i, ChronoUnit.YEARS).atStartOfDay());
            optionalDailyWeather.ifPresent(dailyWeathers::add);
        }
        return dailyWeathers;
    }

    private double calculateTempAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getTempAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateTempAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getTempAvg)
                        .toArray()
                );
    }

    private double calculateFeelsLikeAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getFeelsLikeAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateFeelsLikeAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getFeelsLikeAvg)
                        .toArray()
                );
    }

    private double calculateHeatIndexAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getHeatIndexAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateHeatIndexAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getHeatIndexAvg)
                        .toArray()
                );
    }

    private double calculateMslPresAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getMslPresAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateMslPresAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getMslPresAvg)
                        .toArray()
                );
    }

    private double calculatePrecip(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getPrecip)
                .sum() / dailyWeathers.size();
    }

    private double calculatePrecipDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getPrecip)
                        .toArray()
                );
    }

    private double calculateSnowfall(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getSnowfall)
                .sum() / dailyWeathers.size();
    }

    private double calculateSnowfallDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getSnowfall)
                        .toArray()
                );
    }

    private double calculateCldCvrAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getCldCvrAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateCldCvrAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getCldCvrAvg)
                        .toArray()
                );
    }

    private double calculateWindSpdAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getWindSpdAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateWindSpdAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getWindSpdAvg)
                        .toArray()
                );
    }

    private double calculateRelHumAvg(Set<DailyWeather> dailyWeathers) {
        return dailyWeathers.stream()
                .mapToDouble(DailyWeather::getRelHumAvg)
                .sum() / dailyWeathers.size();
    }

    private double calculateRelHumAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .mapToDouble(DailyWeather::getRelHumAvg)
                        .toArray()
                );
    }
}

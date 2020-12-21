package io.github.batetolast1.wedderforecast.service.weather.impl;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import io.github.batetolast1.wedderforecast.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.repository.weather.HourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.repository.weather.PredictedDailyWeatherRepository;
import io.github.batetolast1.wedderforecast.repository.weather.PredictedHourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.rating.RatingService;
import io.github.batetolast1.wedderforecast.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional

@Log4j2
@RequiredArgsConstructor
public class DefaultWeatherService implements WeatherService {

    private static final int YEARS_BACK_TO_CHECK_DAILY_WEATHERS = 5;
    private static final int YEARS_BACK_TO_CHECK_HOURLY_WEATHERS = 3;

    private final RatingService ratingService;

    private final DailyWeatherRepository dailyWeatherRepository;
    private final PredictedDailyWeatherRepository predictedDailyWeatherRepository;
    private final HourlyWeatherRepository hourlyWeatherRepository;
    private final PredictedHourlyWeatherRepository predictedHourlyWeatherRepository;

    @Override
    public PredictedDailyWeather predictDailyWeather(PostalCoordinate postalCoordinate, LocalDateTime localDateTime) {
        Set<DailyWeather> dailyWeathers = getPastDailyWeathers(postalCoordinate, localDateTime);

        PredictedDailyWeather predictedDailyWeather = new PredictedDailyWeather();
        predictedDailyWeather.setPostalCoordinate(postalCoordinate);
        predictedDailyWeather.setLocalDateTime(localDateTime);

        predictedDailyWeather.setTempAvg(calculateTempAvg(dailyWeathers));
        predictedDailyWeather.setFeelsLikeAvg(calculateFeelsLikeAvg(dailyWeathers));
        predictedDailyWeather.setMslPresAvg(calculateMslPresAvg(dailyWeathers));
        predictedDailyWeather.setPrecip(calculateDailyPrecip(dailyWeathers));
        predictedDailyWeather.setSnowfall(calculateDailySnowfall(dailyWeathers));
        predictedDailyWeather.setCldCvrAvg(calculateCldCvrAvg(dailyWeathers));
        predictedDailyWeather.setWindSpdAvg(calculateWindSpdAvg(dailyWeathers));
        predictedDailyWeather.setRelHumAvg(calculateRelHumAvg(dailyWeathers));

        predictedDailyWeather.setTempAvgDeviation(calculateTempAvgDeviation(dailyWeathers));
        predictedDailyWeather.setFeelsLikeAvgDeviation(calculateFeelsLikeAvgDeviation(dailyWeathers));
        predictedDailyWeather.setMslPresAvgDeviation(calculateMslPresAvgDeviation(dailyWeathers));
        predictedDailyWeather.setPrecipDeviation(calculateDailyPrecipDeviation(dailyWeathers));
        predictedDailyWeather.setSnowfallDeviation(calculateDailySnowfallDeviation(dailyWeathers));
        predictedDailyWeather.setCldCvrAvgDeviation(calculateCldCvrAvgDeviation(dailyWeathers));
        predictedDailyWeather.setWindSpdAvgDeviation(calculateWindSpdAvgDeviation(dailyWeathers));
        predictedDailyWeather.setRelHumAvgDeviation(calculateRelHumAvgDeviation(dailyWeathers));

        predictedDailyWeather.setSystemRating(ratingService.getDailyWeatherSystemRating(predictedDailyWeather));

        return predictedDailyWeatherRepository.save(predictedDailyWeather);
    }

    public Set<DailyWeather> getPastDailyWeathers(PostalCoordinate postalCoordinate, LocalDateTime localDateTime) {
        Set<DailyWeather> dailyWeathers = new HashSet<>();
        for (int yearsBack = 0; yearsBack < YEARS_BACK_TO_CHECK_DAILY_WEATHERS; yearsBack++) {
            dailyWeatherRepository
                    .findByPostalCoordinateAndLocalDateTime(postalCoordinate, localDateTime.withYear(LocalDateTime.now().getYear()).minus(yearsBack, ChronoUnit.YEARS))
                    .ifPresent(dailyWeather -> {
                        if (dailyWeather.getSystemRating() == null) {
                            dailyWeather.setSystemRating(ratingService.getDailyWeatherSystemRating(dailyWeather));
                        }
                        dailyWeathers.add(dailyWeather);
                    });
        }
        return dailyWeathers;
    }

    private double calculateTempAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getTempAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getTempAvg() != null)
                .mapToDouble(DailyWeather::getTempAvg)
                .sum() / count;
    }

    private double calculateTempAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getTempAvg() != null)
                        .mapToDouble(DailyWeather::getTempAvg)
                        .toArray()
                );
    }

    private double calculateFeelsLikeAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getFeelsLikeAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getFeelsLikeAvg() != null)
                .mapToDouble(DailyWeather::getFeelsLikeAvg)
                .sum() / count;
    }

    private double calculateFeelsLikeAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getFeelsLikeAvg() != null)
                        .mapToDouble(DailyWeather::getFeelsLikeAvg)
                        .toArray()
                );
    }

    private double calculateMslPresAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getMslPresAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getMslPresAvg() != null)
                .mapToDouble(DailyWeather::getMslPresAvg)
                .sum() / count;
    }

    private double calculateMslPresAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getMslPresAvg() != null)
                        .mapToDouble(DailyWeather::getMslPresAvg)
                        .toArray()
                );
    }

    private double calculateDailyPrecip(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                .mapToDouble(DailyWeather::getPrecip)
                .sum() / count;
    }

    private double calculateDailyPrecipDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                        .mapToDouble(DailyWeather::getPrecip)
                        .toArray()
                );
    }

    private double calculateDailySnowfall(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                .mapToDouble(DailyWeather::getSnowfall)
                .sum() / count;
    }

    private double calculateDailySnowfallDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                        .mapToDouble(DailyWeather::getSnowfall)
                        .toArray()
                );
    }

    private double calculateCldCvrAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getCldCvrAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getCldCvrAvg() != null)
                .mapToDouble(DailyWeather::getCldCvrAvg)
                .sum() / count;
    }

    private double calculateCldCvrAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getCldCvrAvg() != null)
                        .mapToDouble(DailyWeather::getCldCvrAvg)
                        .toArray()
                );
    }

    private double calculateWindSpdAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getWindSpdAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getWindSpdAvg() != null)
                .mapToDouble(DailyWeather::getWindSpdAvg)
                .sum() / count;
    }

    private double calculateWindSpdAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getWindSpdAvg() != null)
                        .mapToDouble(DailyWeather::getWindSpdAvg)
                        .toArray()
                );
    }

    private double calculateRelHumAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getRelHumAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getRelHumAvg() != null)
                .mapToDouble(DailyWeather::getRelHumAvg)
                .sum() / count;
    }

    private double calculateRelHumAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getRelHumAvg() != null)
                        .mapToDouble(DailyWeather::getRelHumAvg)
                        .toArray()
                );
    }

    @Override
    public PredictedHourlyWeather predictHourlyWeather(PostalCoordinate postalCoordinate, LocalDateTime localDateTime) {
        Set<HourlyWeather> hourlyWeathers = getPastHourlyWeathers(postalCoordinate, localDateTime);

        PredictedHourlyWeather predictedHourlyWeather = new PredictedHourlyWeather();
        predictedHourlyWeather.setPostalCoordinate(postalCoordinate);
        predictedHourlyWeather.setLocalDateTime(localDateTime);

        predictedHourlyWeather.setTemp(calculateTemp(hourlyWeathers));
        predictedHourlyWeather.setFeelsLike(calculateFeelsLike(hourlyWeathers));
        predictedHourlyWeather.setMslPres(calculateMslPres(hourlyWeathers));
        predictedHourlyWeather.setPrecip(calculateHourlyPrecip(hourlyWeathers));
        predictedHourlyWeather.setSnowfall(calculateHourlySnowfall(hourlyWeathers));
        predictedHourlyWeather.setCldCvr(calculateCldCvr(hourlyWeathers));
        predictedHourlyWeather.setWindSpd(calculateWindSpd(hourlyWeathers));
        predictedHourlyWeather.setRelHum(calculateRelHum(hourlyWeathers));

        predictedHourlyWeather.setTempDeviation(calculateTempDeviation(hourlyWeathers));
        predictedHourlyWeather.setFeelsLikeDeviation(calculateFeelsLikeDeviation(hourlyWeathers));
        predictedHourlyWeather.setMslPresDeviation(calculateMslPresDeviation(hourlyWeathers));
        predictedHourlyWeather.setPrecipDeviation(calculateHourlyPrecipDeviation(hourlyWeathers));
        predictedHourlyWeather.setSnowfallDeviation(calculateHourlySnowfallDeviation(hourlyWeathers));
        predictedHourlyWeather.setCldCvrDeviation(calculateCldCvrDeviation(hourlyWeathers));
        predictedHourlyWeather.setWindSpdDeviation(calculateWindSpdDeviation(hourlyWeathers));
        predictedHourlyWeather.setRelHumDeviation(calculateRelHumDeviation(hourlyWeathers));

        predictedHourlyWeather.setSystemRating(ratingService.getHourlyWeatherSystemRating(predictedHourlyWeather));

        return predictedHourlyWeatherRepository.save(predictedHourlyWeather);
    }

    public Set<HourlyWeather> getPastHourlyWeathers(PostalCoordinate postalCoordinate, LocalDateTime localDateTime) {
        Set<HourlyWeather> hourlyWeathers = new HashSet<>();
        for (int yearsBack = 0; yearsBack < YEARS_BACK_TO_CHECK_HOURLY_WEATHERS; yearsBack++) {
            Optional<HourlyWeather> optionalHourlyWeather = hourlyWeatherRepository.findByPostalCoordinateAndLocalDateTime(postalCoordinate, localDateTime.withYear(LocalDateTime.now().getYear()).minus(yearsBack, ChronoUnit.YEARS));
            optionalHourlyWeather.ifPresent(hourlyWeather -> {
                if (hourlyWeather.getSystemRating() == null) {
                    hourlyWeather.setSystemRating(ratingService.getHourlyWeatherSystemRating(hourlyWeather));
                }
                hourlyWeathers.add(hourlyWeather);
            });
        }
        return hourlyWeathers;
    }

    private double calculateTemp(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getTemp() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getTemp() != null)
                .mapToDouble(HourlyWeather::getTemp)
                .sum() / count;
    }

    private double calculateTempDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getTemp() != null)
                        .mapToDouble(HourlyWeather::getTemp)
                        .toArray()
                );
    }

    private double calculateFeelsLike(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getFeelsLike() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getFeelsLike() != null)
                .mapToDouble(HourlyWeather::getFeelsLike)
                .sum() / count;
    }

    private double calculateFeelsLikeDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getFeelsLike() != null)
                        .mapToDouble(HourlyWeather::getFeelsLike)
                        .toArray()
                );
    }

    private double calculateMslPres(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getMslPres() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getMslPres() != null)
                .mapToDouble(HourlyWeather::getMslPres)
                .sum() / count;
    }

    private double calculateMslPresDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getMslPres() != null)
                        .mapToDouble(HourlyWeather::getMslPres)
                        .toArray()
                );
    }

    private double calculateHourlyPrecip(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                .mapToDouble(HourlyWeather::getPrecip)
                .sum() / count;
    }

    private double calculateHourlyPrecipDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getPrecip() != null)
                        .mapToDouble(HourlyWeather::getPrecip)
                        .toArray()
                );
    }

    private double calculateHourlySnowfall(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                .mapToDouble(HourlyWeather::getSnowfall)
                .sum() / count;
    }

    private double calculateHourlySnowfallDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getSnowfall() != null)
                        .mapToDouble(HourlyWeather::getSnowfall)
                        .toArray()
                );
    }

    private double calculateCldCvr(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getCldCvr() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getCldCvr() != null)
                .mapToDouble(HourlyWeather::getCldCvr)
                .sum() / count;
    }

    private double calculateCldCvrDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getCldCvr() != null)
                        .mapToDouble(HourlyWeather::getCldCvr)
                        .toArray()
                );
    }

    private double calculateWindSpd(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getWindSpd() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getWindSpd() != null)
                .mapToDouble(HourlyWeather::getWindSpd)
                .sum() / count;
    }

    private double calculateWindSpdDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getWindSpd() != null)
                        .mapToDouble(HourlyWeather::getWindSpd)
                        .toArray()
                );
    }

    private double calculateRelHum(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getRelHum() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getRelHum() != null)
                .mapToDouble(HourlyWeather::getRelHum)
                .sum() / count;
    }

    private double calculateRelHumDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getRelHum() != null)
                        .mapToDouble(HourlyWeather::getRelHum)
                        .toArray()
                );
    }
}

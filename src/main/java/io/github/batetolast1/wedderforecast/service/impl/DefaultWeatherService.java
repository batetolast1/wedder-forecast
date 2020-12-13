package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedHourlyWeather;
import io.github.batetolast1.wedderforecast.model.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.model.repository.weather.HourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.model.repository.weather.PredictedDailyWeatherRepository;
import io.github.batetolast1.wedderforecast.model.repository.weather.PredictedHourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.RatingService;
import io.github.batetolast1.wedderforecast.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    private static final int YEARS_TO_CHECK_DAILY_WEATHER = 5;
    private static final int YEARS_TO_CHECK_HOURLY_WEATHER = 5;

    private final RatingService ratingService;

    private final DailyWeatherRepository dailyWeatherRepository;
    private final PredictedDailyWeatherRepository predictedDailyWeatherRepository;
    private final HourlyWeatherRepository hourlyWeatherRepository;
    private final PredictedHourlyWeatherRepository predictedHourlyWeatherRepository;

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
        predictedDailyWeather.setPrecip(calculateDailyPrecip(dailyWeathers));
        predictedDailyWeather.setSnowfall(calculateDailySnowfall(dailyWeathers));
        predictedDailyWeather.setCldCvrAvg(calculateCldCvrAvg(dailyWeathers));
        predictedDailyWeather.setWindSpdAvg(calculateWindSpdAvg(dailyWeathers));
        predictedDailyWeather.setRelHumAvg(calculateRelHumAvg(dailyWeathers));

        predictedDailyWeather.setTempAvgDeviation(calculateTempAvgDeviation(dailyWeathers));
        predictedDailyWeather.setFeelsLikeAvgDeviation(calculateFeelsLikeAvgDeviation(dailyWeathers));
        predictedDailyWeather.setHeatIndexAvgDeviation(calculateHeatIndexAvgDeviation(dailyWeathers));
        predictedDailyWeather.setMslPresAvgDeviation(calculateMslPresAvgDeviation(dailyWeathers));
        predictedDailyWeather.setPrecipDeviation(calculateDailyPrecipDeviation(dailyWeathers));
        predictedDailyWeather.setSnowfallDeviation(calculateDailySnowfallDeviation(dailyWeathers));
        predictedDailyWeather.setCldCvrAvgDeviation(calculateCldCvrAvgDeviation(dailyWeathers));
        predictedDailyWeather.setWindSpdAvgDeviation(calculateWindSpdAvgDeviation(dailyWeathers));
        predictedDailyWeather.setRelHumAvgDeviation(calculateRelHumAvgDeviation(dailyWeathers));

        predictedDailyWeather.setSystemRating(ratingService.rateDailyWeather(predictedDailyWeather));

        return predictedDailyWeatherRepository.save(predictedDailyWeather);
    }

    public Set<DailyWeather> getDailyWeathersForLocationAndLocalDate(Location location, LocalDate localDate) {
        Set<DailyWeather> dailyWeathers = new HashSet<>();
        for (int i = 1; i <= YEARS_TO_CHECK_DAILY_WEATHER; i++) {
            Optional<DailyWeather> optionalDailyWeather = dailyWeatherRepository.findByLocationAndTimestamp(location, localDate.minus(i, ChronoUnit.YEARS).atStartOfDay());
            optionalDailyWeather.ifPresent(dailyWeathers::add);
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

    private double calculateHeatIndexAvg(Set<DailyWeather> dailyWeathers) {
        long count = dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getHeatIndexAvg() != null)
                .count();
        return dailyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getHeatIndexAvg() != null)
                .mapToDouble(DailyWeather::getHeatIndexAvg)
                .sum() / count;
    }

    private double calculateHeatIndexAvgDeviation(Set<DailyWeather> dailyWeathers) {
        return new StandardDeviation()
                .evaluate(dailyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getHeatIndexAvg() != null)
                        .mapToDouble(DailyWeather::getHeatIndexAvg)
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
    public PredictedHourlyWeather predictHourlyWeather(Location location, LocalDateTime localDateTime) {
        Set<HourlyWeather> hourlyWeathers = getHourlyWeathersForLocationAndLocalDateTime(location, localDateTime);

        PredictedHourlyWeather predictedHourlyWeather = new PredictedHourlyWeather();
        predictedHourlyWeather.setLocation(location);
        predictedHourlyWeather.setTimestamp(localDateTime);

        predictedHourlyWeather.setTemp(calculateTemp(hourlyWeathers));
        predictedHourlyWeather.setFeelsLike(calculateFeelsLike(hourlyWeathers));
        predictedHourlyWeather.setHeatIndex(calculateHeatIndex(hourlyWeathers));
        predictedHourlyWeather.setMslPres(calculateMslPres(hourlyWeathers));
        predictedHourlyWeather.setPrecip(calculateHourlyPrecip(hourlyWeathers));
        predictedHourlyWeather.setSnowfall(calculateHourlySnowfall(hourlyWeathers));
        predictedHourlyWeather.setCldCvr(calculateCldCvr(hourlyWeathers));
        predictedHourlyWeather.setWindSpd(calculateWindSpd(hourlyWeathers));
        predictedHourlyWeather.setRelHum(calculateRelHum(hourlyWeathers));

        predictedHourlyWeather.setTempDeviation(calculateTempDeviation(hourlyWeathers));
        predictedHourlyWeather.setFeelsLikeDeviation(calculateFeelsLikeDeviation(hourlyWeathers));
        predictedHourlyWeather.setHeatIndexDeviation(calculateHeatIndexDeviation(hourlyWeathers));
        predictedHourlyWeather.setMslPresDeviation(calculateMslPresDeviation(hourlyWeathers));
        predictedHourlyWeather.setPrecipDeviation(calculateHourlyPrecipDeviation(hourlyWeathers));
        predictedHourlyWeather.setSnowfallDeviation(calculateHourlySnowfallDeviation(hourlyWeathers));
        predictedHourlyWeather.setCldCvrDeviation(calculateCldCvrDeviation(hourlyWeathers));
        predictedHourlyWeather.setWindSpdDeviation(calculateWindSpdDeviation(hourlyWeathers));
        predictedHourlyWeather.setRelHumDeviation(calculateRelHumDeviation(hourlyWeathers));

        predictedHourlyWeather.setSystemRating(ratingService.rateHourlyWeather(predictedHourlyWeather));

        return predictedHourlyWeatherRepository.save(predictedHourlyWeather);
    }

    public Set<HourlyWeather> getHourlyWeathersForLocationAndLocalDateTime(Location location, LocalDateTime localDateTime) {
        Set<HourlyWeather> hourlyWeathers = new HashSet<>();
        for (int i = 1; i <= YEARS_TO_CHECK_HOURLY_WEATHER; i++) {
            Optional<HourlyWeather> optionalHourlyWeather = hourlyWeatherRepository.findByLocationAndTimestamp(location, localDateTime.minus(i, ChronoUnit.YEARS));
            optionalHourlyWeather.ifPresent(hourlyWeathers::add);
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

    private double calculateHeatIndex(Set<HourlyWeather> hourlyWeathers) {
        long count = hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getHeatIndex() != null)
                .count();
        return hourlyWeathers.stream()
                .filter(dailyWeather -> dailyWeather.getHeatIndex() != null)
                .mapToDouble(HourlyWeather::getHeatIndex)
                .sum() / count;
    }

    private double calculateHeatIndexDeviation(Set<HourlyWeather> hourlyWeathers) {
        return new StandardDeviation()
                .evaluate(hourlyWeathers.stream()
                        .filter(dailyWeather -> dailyWeather.getHeatIndex() != null)
                        .mapToDouble(HourlyWeather::getHeatIndex)
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

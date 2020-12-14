package io.github.batetolast1.wedderforecast.service.rating.impl;

import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.rating.enums.OverallSystemRatingValue;
import io.github.batetolast1.wedderforecast.model.rating.enums.SystemRatingValue;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.repository.rating.SystemRatingRepository;
import io.github.batetolast1.wedderforecast.service.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary

@RequiredArgsConstructor
public class DefaultRatingService implements RatingService {

    private final SystemRatingRepository systemRatingRepository;

    @Override
    public SystemRating rateDailyWeather(DailyWeather dailyWeather) {
        SystemRating systemRating = new SystemRating();
        rateDailyTempAvg(dailyWeather.getTempAvg(), systemRating);
        rateDailyFeelsLikeAvg(dailyWeather.getFeelsLikeAvg(), systemRating);
        rateDailyHeatIndexAvg(dailyWeather.getHeatIndexAvg(), systemRating);
        rateDailyMslPresAvg(dailyWeather.getMslPresAvg(), systemRating);
        rateDailyPrecip(dailyWeather.getPrecip(), systemRating);
        rateDailySnowfall(dailyWeather.getSnowfall(), systemRating);
        rateDailyCldCvrAvg(dailyWeather.getCldCvrAvg(), systemRating);
        rateDailyWindSpdAvg(dailyWeather.getWindSpdAvg(), systemRating);
        rateDailyRelHumAvg(dailyWeather.getRelHumAvg(), systemRating);
        rateOverallDailyWeather(dailyWeather, systemRating);
        return systemRatingRepository.save(systemRating);
    }

    private void rateDailyTempAvg(Double tempAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (tempAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (tempAvg > 75) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (tempAvg > 70) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (tempAvg > 65) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (tempAvg > 60) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setTemp(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyFeelsLikeAvg(Double feelsLikeAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (feelsLikeAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (feelsLikeAvg > 70) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (feelsLikeAvg > 65) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (feelsLikeAvg > 60) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (feelsLikeAvg > 50) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setFeelsLike(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyHeatIndexAvg(Double heatIndexAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (heatIndexAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (heatIndexAvg > 75) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (heatIndexAvg > 70) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (heatIndexAvg > 60) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (heatIndexAvg > 50) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setHeatIndex(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyMslPresAvg(Double mslPresAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (mslPresAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (mslPresAvg > 1030) {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        } else if (mslPresAvg > 1025) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else if (mslPresAvg > 1020) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (mslPresAvg > 1015) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (mslPresAvg > 1010) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (mslPresAvg > 1005) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (mslPresAvg > 1000) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (mslPresAvg > 995) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setMslPres(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyPrecip(Double precip, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (precip == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (precip == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (precip < 0.02) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (precip < 0.04) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (precip < 0.1) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setPrecip(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailySnowfall(Double snowfall, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (snowfall == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (snowfall == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setSnowfall(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyCldCvrAvg(Double cldCvrAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (cldCvrAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (cldCvrAvg == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (cldCvrAvg < 10) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (cldCvrAvg < 20) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (cldCvrAvg < 30) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setCldCvr(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyWindSpdAvg(Double windSpdAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (windSpdAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (windSpdAvg < 4) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (windSpdAvg < 5) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (windSpdAvg < 6) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (windSpdAvg < 7) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setWindSpd(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyRelHumAvg(Double relHumAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (relHumAvg == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (relHumAvg > 95) {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        } else if (relHumAvg > 90) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else if (relHumAvg > 87) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (relHumAvg > 85) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (relHumAvg > 80) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (relHumAvg > 75) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (relHumAvg > 77) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (relHumAvg > 70) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setRelHum(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateOverallDailyWeather(DailyWeather dailyWeather, SystemRating systemRating) {
        if (dailyWeather.getTempAvg() == null
                || dailyWeather.getFeelsLikeAvg() == null
                || dailyWeather.getHeatIndexAvg() == null
                || dailyWeather.getMslPresAvg() == null
                || dailyWeather.getPrecip() == null
                || dailyWeather.getSnowfall() == null
                || dailyWeather.getCldCvrAvg() == null
                || dailyWeather.getWindSpdAvg() == null
                || dailyWeather.getRelHumAvg() == null) {
            systemRating.setOverall(OverallSystemRatingValue.INCOMPLETE_DATA);
        } else {
            int totalPoints = systemRating.getTotalPoints();
            if (totalPoints > 35) {
                systemRating.setOverall(OverallSystemRatingValue.TEN);
            } else if (totalPoints > 32) {
                systemRating.setOverall(OverallSystemRatingValue.NINE);
            } else if (totalPoints > 29) {
                systemRating.setOverall(OverallSystemRatingValue.EIGHT);
            } else if (totalPoints > 26) {
                systemRating.setOverall(OverallSystemRatingValue.SEVEN);
            } else if (totalPoints > 23) {
                systemRating.setOverall(OverallSystemRatingValue.SIX);
            } else if (totalPoints > 20) {
                systemRating.setOverall(OverallSystemRatingValue.FIVE);
            } else if (totalPoints > 17) {
                systemRating.setOverall(OverallSystemRatingValue.FOUR);
            } else if (totalPoints > 14) {
                systemRating.setOverall(OverallSystemRatingValue.THREE);
            } else if (totalPoints > 13) {
                systemRating.setOverall(OverallSystemRatingValue.TWO);
            } else {
                systemRating.setOverall(OverallSystemRatingValue.ONE);
            }
        }
    }

    @Override
    public SystemRating rateHourlyWeather(HourlyWeather hourlyWeather) {
        SystemRating systemRating = new SystemRating();
        rateHourlyTemp(hourlyWeather.getTemp(), systemRating);
        rateHourlyFeelsLike(hourlyWeather.getFeelsLike(), systemRating);
        rateHourlyHeatIndex(hourlyWeather.getHeatIndex(), systemRating);
        rateHourlyMslPres(hourlyWeather.getMslPres(), systemRating);
        rateHourlyPrecip(hourlyWeather.getPrecip(), systemRating);
        rateHourlySnowfall(hourlyWeather.getSnowfall(), systemRating);
        rateHourlyCldCvr(hourlyWeather.getCldCvr(), systemRating);
        rateHourlyWindSpd(hourlyWeather.getWindSpd(), systemRating);
        rateHourlyRelHum(hourlyWeather.getRelHum(), systemRating);
        rateOverallHourlyWeather(hourlyWeather, systemRating);
        return systemRatingRepository.save(systemRating);
    }

    private void rateHourlyTemp(Double temp, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (temp == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (temp > 75) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (temp > 70) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (temp > 65) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (temp > 60) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setTemp(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyFeelsLike(Double feelsLike, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (feelsLike == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (feelsLike > 70) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (feelsLike > 65) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (feelsLike > 60) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (feelsLike > 50) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setFeelsLike(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyHeatIndex(Double heatIndex, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (heatIndex == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (heatIndex > 75) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (heatIndex > 70) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (heatIndex > 60) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (heatIndex > 50) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setHeatIndex(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyMslPres(Double mslPres, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (mslPres == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (mslPres > 1030) {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        } else if (mslPres > 1025) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else if (mslPres > 1020) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (mslPres > 1015) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (mslPres > 1010) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (mslPres > 1005) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (mslPres > 1000) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (mslPres > 995) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setMslPres(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyPrecip(Double precip, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (precip == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (precip == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (precip < 0.02) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (precip < 0.04) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (precip < 0.1) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setPrecip(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlySnowfall(Double snowfall, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (snowfall == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (snowfall == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setSnowfall(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyCldCvr(Double cldCvr, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (cldCvr == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (cldCvr == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (cldCvr < 10) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (cldCvr < 20) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (cldCvr < 30) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setCldCvr(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyWindSpd(Double windSpd, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (windSpd == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (windSpd < 4) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (windSpd < 5) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (windSpd < 6) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (windSpd < 7) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setWindSpd(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHourlyRelHum(Double relHum, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (relHum == null) {
            systemRatingValue = SystemRatingValue.MISSING_DATA;
        } else if (relHum > 95) {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        } else if (relHum > 90) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else if (relHum > 87) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (relHum > 85) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (relHum > 80) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else if (relHum > 75) {
            systemRatingValue = SystemRatingValue.SATISFIED;
        } else if (relHum > 77) {
            systemRatingValue = SystemRatingValue.NEUTRAL;
        } else if (relHum > 70) {
            systemRatingValue = SystemRatingValue.UNSATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setRelHum(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateOverallHourlyWeather(HourlyWeather hourlyWeather, SystemRating systemRating) {
        if (hourlyWeather.getTemp() == null
                || hourlyWeather.getFeelsLike() == null
                || hourlyWeather.getHeatIndex() == null
                || hourlyWeather.getMslPres() == null
                || hourlyWeather.getPrecip() == null
                || hourlyWeather.getSnowfall() == null
                || hourlyWeather.getCldCvr() == null
                || hourlyWeather.getWindSpd() == null
                || hourlyWeather.getRelHum() == null) {
            systemRating.setOverall(OverallSystemRatingValue.INCOMPLETE_DATA);
        } else {
            int totalPoints = systemRating.getTotalPoints();
            if (totalPoints > 35) {
                systemRating.setOverall(OverallSystemRatingValue.TEN);
            } else if (totalPoints > 32) {
                systemRating.setOverall(OverallSystemRatingValue.NINE);
            } else if (totalPoints > 29) {
                systemRating.setOverall(OverallSystemRatingValue.EIGHT);
            } else if (totalPoints > 26) {
                systemRating.setOverall(OverallSystemRatingValue.SEVEN);
            } else if (totalPoints > 23) {
                systemRating.setOverall(OverallSystemRatingValue.SIX);
            } else if (totalPoints > 20) {
                systemRating.setOverall(OverallSystemRatingValue.FIVE);
            } else if (totalPoints > 17) {
                systemRating.setOverall(OverallSystemRatingValue.FOUR);
            } else if (totalPoints > 14) {
                systemRating.setOverall(OverallSystemRatingValue.THREE);
            } else if (totalPoints > 13) {
                systemRating.setOverall(OverallSystemRatingValue.TWO);
            } else {
                systemRating.setOverall(OverallSystemRatingValue.ONE);
            }
        }
    }
}

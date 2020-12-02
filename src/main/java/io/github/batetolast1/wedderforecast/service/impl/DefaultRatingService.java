package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.model.entity.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.entity.rating.enums.OverallSystemRatingValue;
import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.repository.rating.SystemRatingRepository;
import io.github.batetolast1.wedderforecast.service.RatingService;
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
        rateOverallDailyWeather(systemRating);
        return systemRatingRepository.save(systemRating);
    }

    private void rateDailyTempAvg(double tempAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (tempAvg > 75) {
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

    private void rateDailyFeelsLikeAvg(double feelsLikeAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (feelsLikeAvg > 70) {
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

    private void rateDailyHeatIndexAvg(double heatIndexAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (heatIndexAvg > 75) {
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

    private void rateDailyMslPresAvg(double mslPresAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (mslPresAvg > 1030) {
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

    private void rateDailyPrecip(double precip, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (precip == 0.0) {
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

    private void rateDailySnowfall(double snowfall, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (snowfall == 0.0) {
            systemRatingValue = SystemRatingValue.VERY_SATISFIED;
        } else {
            systemRatingValue = SystemRatingValue.VERY_UNSATISFIED;
        }
        systemRating.setSnowfall(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateDailyCldCvrAvg(double cldCvrAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (cldCvrAvg == 0.0) {
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

    private void rateDailyWindSpdAvg(double windSpdAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (windSpdAvg < 4) {
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

    private void rateDailyRelHumAvg(double relHumAvg, SystemRating systemRating) {
        SystemRatingValue systemRatingValue;
        if (relHumAvg > 95) {
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

    private void rateOverallDailyWeather(SystemRating systemRating) {
        int totalPoints = systemRating.getTotalPoints();
        OverallSystemRatingValue overall;
        if (totalPoints > 35) {
            overall = OverallSystemRatingValue.TEN;
        } else if (totalPoints > 32) {
            overall = OverallSystemRatingValue.NINE;
        } else if (totalPoints > 29) {
            overall = OverallSystemRatingValue.EIGHT;
        } else if (totalPoints > 26) {
            overall = OverallSystemRatingValue.SEVEN;
        } else if (totalPoints > 23) {
            overall = OverallSystemRatingValue.SIX;
        } else if (totalPoints > 20) {
            overall = OverallSystemRatingValue.FIVE;
        } else if (totalPoints > 17) {
            overall = OverallSystemRatingValue.FOUR;
        } else if (totalPoints > 14) {
            overall = OverallSystemRatingValue.THREE;
        } else if (totalPoints > 13) {
            overall = OverallSystemRatingValue.TWO;
        } else {
            overall = OverallSystemRatingValue.ONE;
        }
        systemRating.setOverall(overall);
    }

    @Override
    public SystemRating rateHourlyWeather(HourlyWeather hourlyWeather) {
        // TODO create algorithms to calculate SystemRating for HourlyWeather
        return null;
    }
}

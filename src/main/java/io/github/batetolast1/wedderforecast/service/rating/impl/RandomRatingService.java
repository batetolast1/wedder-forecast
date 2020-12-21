package io.github.batetolast1.wedderforecast.service.rating.impl;

import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.rating.enums.OverallSystemRatingValue;
import io.github.batetolast1.wedderforecast.model.rating.enums.SystemRatingValue;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.repository.rating.SystemRatingRepository;
import io.github.batetolast1.wedderforecast.service.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional

@RequiredArgsConstructor
public class RandomRatingService implements RatingService {

    private final SystemRatingRepository systemRatingRepository;
    private final Random random = new Random();

    @Override
    public SystemRating getDailyWeatherSystemRating(DailyWeather dailyWeather) {
        SystemRating systemRating = generateRandomSystemRating();
        systemRatingRepository.save(systemRating);
        return systemRating;
    }

    @Override
    public SystemRating getHourlyWeatherSystemRating(HourlyWeather hourlyWeather) {
        SystemRating systemRating = generateRandomSystemRating();
        systemRatingRepository.save(systemRating);
        return systemRating;
    }

    private SystemRating generateRandomSystemRating() {
        SystemRating systemRating = new SystemRating();
        rateTemp(systemRating);
        rateFeelsLike(systemRating);
        rateMslPres(systemRating);
        ratePrecip(systemRating);
        rateSnowfall(systemRating);
        rateCldCvr(systemRating);
        rateWindSpd(systemRating);
        rateRelHum(systemRating);
        rateOverall(systemRating);
        return systemRating;
    }

    private void rateTemp(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setTemp(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateFeelsLike(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setFeelsLike(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateMslPres(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setMslPres(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void ratePrecip(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setPrecip(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateSnowfall(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setSnowfall(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateCldCvr(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setCldCvr(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateWindSpd(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setWindSpd(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateRelHum(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setRelHum(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateOverall(SystemRating systemRating) {
        OverallSystemRatingValue overall = generateRandomOverallSystemRatingValue();
        systemRating.setOverall(overall);
    }

    private SystemRatingValue generateRandomSystemRatingValue() {
        int value = random.ints(1, 6)
                .findFirst()
                .orElse(5);

        return switch (value) {
            case 1 -> SystemRatingValue.ONE;
            case 2 -> SystemRatingValue.TWO;
            case 3 -> SystemRatingValue.THREE;
            case 4 -> SystemRatingValue.FOUR;
            default -> SystemRatingValue.FIVE;
        };
    }

    private OverallSystemRatingValue generateRandomOverallSystemRatingValue() {
        int value = random.ints(1, 11)
                .findFirst()
                .orElse(10);

        return switch (value) {
            case 1 -> OverallSystemRatingValue.ONE;
            case 2 -> OverallSystemRatingValue.TWO;
            case 3 -> OverallSystemRatingValue.THREE;
            case 4 -> OverallSystemRatingValue.FOUR;
            case 5 -> OverallSystemRatingValue.FIVE;
            case 6 -> OverallSystemRatingValue.SIX;
            case 7 -> OverallSystemRatingValue.SEVEN;
            case 8 -> OverallSystemRatingValue.EIGHT;
            case 9 -> OverallSystemRatingValue.NINE;
            default -> OverallSystemRatingValue.TEN;
        };
    }
}

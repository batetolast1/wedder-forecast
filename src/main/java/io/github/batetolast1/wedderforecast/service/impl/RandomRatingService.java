package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.model.entity.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.repository.rating.SystemRatingRepository;
import io.github.batetolast1.wedderforecast.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Primary

@RequiredArgsConstructor
public class RandomRatingService implements RatingService {

    private final SystemRatingRepository systemRatingRepository;
    private final Random random = new Random();

    @Override
    public SystemRating rateDailyWeather(DailyWeather dailyWeather) {
        SystemRating systemRating = generateRandomSystemRating();
        systemRatingRepository.save(systemRating);
        return systemRating;
    }

    @Override
    public SystemRating rateHour(HourlyWeather hourlyWeather) {
        SystemRating systemRating = generateRandomSystemRating();
        systemRatingRepository.save(systemRating);
        return systemRating;
    }

    private SystemRating generateRandomSystemRating() {
        SystemRating systemRating = new SystemRating();
        rateOverall(systemRating);
        rateTemp(systemRating);
        ratePres(systemRating);
        ratePrecip(systemRating);
        rateCld(systemRating);
        rateWind(systemRating);
        rateHum(systemRating);
        return systemRating;
    }

    private void rateOverall(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setOverall(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateTemp(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setTemp(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void ratePres(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setPres(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void ratePrecip(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setPrecip(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateCld(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setCld(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateWind(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setWind(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private void rateHum(SystemRating systemRating) {
        SystemRatingValue systemRatingValue = generateRandomSystemRatingValue();
        systemRating.setHum(systemRatingValue);
        systemRating.addPoints(systemRatingValue.getPoints());
    }

    private SystemRatingValue generateRandomSystemRatingValue() {
        int value = random.ints(1, 6)
                .findFirst()
                .orElse(5);

        return switch (value) {
            case 1 -> SystemRatingValue.VERY_UNSATISFIED;
            case 2 -> SystemRatingValue.UNSATISFIED;
            case 3 -> SystemRatingValue.NEUTRAL;
            case 4 -> SystemRatingValue.SATISFIED;
            default -> SystemRatingValue.VERY_SATISFIED;
        };
    }
}

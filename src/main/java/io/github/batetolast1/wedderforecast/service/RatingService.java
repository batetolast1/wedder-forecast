package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.model.entity.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;

public interface RatingService {

    SystemRating rateDailyWeather(DailyWeather dailyWeather);

    SystemRating rateHour(HourlyWeather hourlyWeather);
}

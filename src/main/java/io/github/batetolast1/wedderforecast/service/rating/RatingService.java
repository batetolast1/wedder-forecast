package io.github.batetolast1.wedderforecast.service.rating;

import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;

public interface RatingService {

    SystemRating rateDailyWeather(DailyWeather dailyWeather);

    SystemRating rateHourlyWeather(HourlyWeather hourlyWeather);
}

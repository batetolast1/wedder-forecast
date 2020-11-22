package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;

import java.util.List;

public interface ApiService {

    List<DailyWeather> getDaysOfYear(String postalCode, String countryCode, int year);

    List<HourlyWeather> getHoursOfYear(String postalCode, String countryCode, int year);
}

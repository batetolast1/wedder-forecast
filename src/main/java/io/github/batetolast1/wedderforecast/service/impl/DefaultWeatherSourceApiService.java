package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.config.property.WeatherSourceApiProperties;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.model.repository.weather.HourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.RatingService;
import io.github.batetolast1.wedderforecast.service.WeatherSourceApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * sample URL: https://api.weathersource.com/v1/{{api_key}}/postal_codes/{{postal_code}},{{country}}/history.json?timestamp_between={{beginning}},{{end}}&fields={{fields}}
 */

@Service
@Transactional

@Log4j2
public class DefaultWeatherSourceApiService implements WeatherSourceApiService {

    private static final int YEARS_TO_FETCH_DAILY_WEATHERS = 5;
    private static final int YEARS_TO_FETCH_HOURLY_WEATHERS = 3;

    private final RatingService ratingService;

    private final DailyWeatherRepository dailyWeatherRepository;
    private final HourlyWeatherRepository hourlyWeatherRepository;

    private final WeatherSourceApiProperties weatherSourceApiProperties;
    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    public DefaultWeatherSourceApiService(RatingService ratingService,
                                          DailyWeatherRepository dailyWeatherRepository,
                                          HourlyWeatherRepository hourlyWeatherRepository,
                                          RestTemplateBuilder restTemplateBuilder,
                                          WeatherSourceApiProperties weatherSourceApiProperties) {
        this.ratingService = ratingService;

        this.dailyWeatherRepository = dailyWeatherRepository;
        this.hourlyWeatherRepository = hourlyWeatherRepository;

        this.weatherSourceApiProperties = weatherSourceApiProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(weatherSourceApiProperties.getHost())
                .path(weatherSourceApiProperties.getPath())
                .query(weatherSourceApiProperties.getTimestampQuery())
                .query(weatherSourceApiProperties.getFieldsQuery())
                .query(weatherSourceApiProperties.getPeriodQuery());
    }

    @Override
    public void getDailyWeathers(Location location, LocalDate localDate) {
        if (!isAnyDailyDataFetched(location)) {
            for (int i = 0; i < YEARS_TO_FETCH_DAILY_WEATHERS; i++) {
                URI uri = buildUriForYearlyDailyWeathers(
                        location,
                        LocalDate.now()
                                .minus(i, ChronoUnit.YEARS)
                                .minus(1, ChronoUnit.DAYS));
                log.debug("URI {}", uri);

                List<DailyWeather> dailyWeathers = fetchDailyWeathers(uri);
                if (dailyWeathers != null) {
                    dailyWeathers.forEach(dailyWeather -> {
                        dailyWeather.setLocation(location);
                        dailyWeather.setSystemRating(ratingService.rateDailyWeather(dailyWeather));
                    });
                    dailyWeatherRepository.saveAll(dailyWeathers);
                }
            }
        }
    }

    private boolean isAnyDailyDataFetched(Location location) {
        return dailyWeatherRepository.existsByLocation(location);
    }

    // TODO add getting only missing data between now and latest available record in DB instead of adding duplicates!
    private boolean isLatestDailyDataFetched(Location location) {
        LocalDateTime localDatetime = LocalDate.now()
                .minus(1, ChronoUnit.DAYS)
                .atStartOfDay();
        return dailyWeatherRepository.existsByLocationAndTimestamp(location, localDatetime);
    }

    private URI buildUriForYearlyDailyWeathers(Location location, LocalDate end) {
        LocalDate beginning = end.minus(1, ChronoUnit.YEARS).plus(1, ChronoUnit.DAYS);
        return uriComponentsBuilder
                .buildAndExpand(
                        weatherSourceApiProperties.getApiKey(),
                        location.getPostalCode(),
                        location.getCountryCode(),
                        beginning,
                        end,
                        weatherSourceApiProperties.getDailyFields(),
                        "day")
                .encode()
                .toUri();
    }

    private List<DailyWeather> fetchDailyWeathers(URI uri) {
        ResponseEntity<List<DailyWeather>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    @Override
    public void getHourlyWeathers(Location location, LocalDate localDate) {
        if (!isAnyHourlyDataFetched(location)) {
            for (int i = 1; i <= YEARS_TO_FETCH_HOURLY_WEATHERS; i++) {
                URI uri = buildUriForYearlyHourlyWeathers(
                        location,
                        LocalDate.now()
                                .minus(i, ChronoUnit.YEARS).
                                minus(1, ChronoUnit.DAYS));
                log.debug("URI {}", uri);

                List<HourlyWeather> hourlyWeathers = fetchHourlyWeathers(uri);
                if (hourlyWeathers != null) {
                    hourlyWeathers.forEach(hourlyWeather -> {
                        hourlyWeather.setLocation(location);
                        hourlyWeather.setSystemRating(ratingService.rateHourlyWeather(hourlyWeather));
                    });
                    hourlyWeatherRepository.saveAll(hourlyWeathers);
                }
            }
        }
    }

    private boolean isAnyHourlyDataFetched(Location location) {
        return hourlyWeatherRepository.existsByLocation(location);
    }

    private boolean isLatestHourlyDataFetched(Location location) {
        LocalDateTime localDatetime = LocalDate.now()
                .minus(1, ChronoUnit.DAYS)
                .atStartOfDay();
        return hourlyWeatherRepository.existsByLocationAndTimestamp(location, localDatetime);
    }

    private URI buildUriForYearlyHourlyWeathers(Location location, LocalDate end) {
        LocalDate beginning = end.minus(1, ChronoUnit.YEARS).plus(1, ChronoUnit.DAYS);
        return uriComponentsBuilder
                .buildAndExpand(
                        weatherSourceApiProperties.getApiKey(),
                        location.getPostalCode(),
                        location.getCountryCode(),
                        beginning,
                        end,
                        weatherSourceApiProperties.getHourlyFields(),
                        "hour")
                .encode()
                .toUri();
    }

    private List<HourlyWeather> fetchHourlyWeathers(URI uri) {
        ResponseEntity<List<HourlyWeather>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}

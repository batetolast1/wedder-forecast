package io.github.batetolast1.wedderforecast.service.api.impl;

import io.github.batetolast1.wedderforecast.config.property.WeatherSourceApiProperties;
import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.repository.weather.DailyWeatherRepository;
import io.github.batetolast1.wedderforecast.repository.weather.HourlyWeatherRepository;
import io.github.batetolast1.wedderforecast.service.api.WeatherSourceApiService;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * sample URL: https://api.weathersource.com/v1/{{api_key}}/postal_codes/{{postal_code}},{{country}}/history.json?timestamp_between={{beginning}},{{end}}&fields={{fields}}
 */

@Service
@Transactional

@Log4j2
public class DefaultWeatherSourceApiService implements WeatherSourceApiService {

    private static final int YEARS_BACK_TO_FETCH_DAILY_WEATHERS = 5;
    private static final int YEARS_BACK_TO_FETCH_HOURLY_WEATHERS = 3;

    private final DailyWeatherRepository dailyWeatherRepository;
    private final HourlyWeatherRepository hourlyWeatherRepository;

    private final WeatherSourceApiProperties weatherSourceApiProperties;
    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    public DefaultWeatherSourceApiService(DailyWeatherRepository dailyWeatherRepository,
                                          HourlyWeatherRepository hourlyWeatherRepository,
                                          WeatherSourceApiProperties weatherSourceApiProperties,
                                          RestTemplateBuilder restTemplateBuilder) {
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
    public void fetchDailyWeathers(PostalCoordinate postalCoordinate) {
        if (!isDailyDataFetched(postalCoordinate)) {
            List<DailyWeather> dailyWeathers = new ArrayList<>(366 * YEARS_BACK_TO_FETCH_DAILY_WEATHERS);

            for (int yearsBack = 0; yearsBack < YEARS_BACK_TO_FETCH_DAILY_WEATHERS; yearsBack++) {
                LocalDate beginning = getBeginningDate(yearsBack);
                LocalDate end = getEndDate(yearsBack);
                URI uri = buildDailyWeatherUri(postalCoordinate, beginning, end);
                log.debug("URI {}", uri);
                dailyWeathers.addAll(getDailyWeathersResponse(uri));
            }

            dailyWeathers.forEach(dailyWeather -> dailyWeather.setPostalCoordinate(postalCoordinate));
            dailyWeatherRepository.saveAll(dailyWeathers);
        }
    }

    private boolean isDailyDataFetched(PostalCoordinate postalCoordinate) {
        return dailyWeatherRepository.existsByPostalCoordinate(postalCoordinate);
    }

    private LocalDate getBeginningDate(int yearsBack) {
        return LocalDate.now()
                .minus(yearsBack + 1L, ChronoUnit.YEARS);
    }

    private LocalDate getEndDate(int yearsBack) {
        return LocalDate.now()
                .minus(yearsBack, ChronoUnit.YEARS)
                .minus(1, ChronoUnit.DAYS);
    }

    private URI buildDailyWeatherUri(PostalCoordinate postalCoordinate, LocalDate beginning, LocalDate end) {
        return uriComponentsBuilder
                .buildAndExpand(
                        weatherSourceApiProperties.getApiKey(),
                        postalCoordinate.getPostalCode(),
                        postalCoordinate.getCountryCode(),
                        beginning,
                        end,
                        weatherSourceApiProperties.getDailyFields(),
                        weatherSourceApiProperties.getPeriodDay())
                .encode()
                .toUri();
    }

    private List<DailyWeather> getDailyWeathersResponse(URI uri) {
        ResponseEntity<List<DailyWeather>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    @Override
    public void fetchHourlyWeathers(PostalCoordinate postalCoordinate) {
        if (!isHourlyDataFetched(postalCoordinate)) {
            List<HourlyWeather> hourlyWeathers = new ArrayList<>(8784 * YEARS_BACK_TO_FETCH_HOURLY_WEATHERS);

            for (int yearsBack = 0; yearsBack < YEARS_BACK_TO_FETCH_HOURLY_WEATHERS; yearsBack++) {
                LocalDate beginning = getBeginningDate(yearsBack);
                LocalDate end = getEndDate(yearsBack);
                URI uri = buildHourlyWeatherUri(postalCoordinate, beginning, end);
                log.debug("URI {}", uri);
                hourlyWeathers.addAll(getHourlyWeathersResponse(uri));
            }

            hourlyWeathers.forEach(hourlyWeather -> hourlyWeather.setPostalCoordinate(postalCoordinate));
            hourlyWeatherRepository.saveAll(hourlyWeathers);
        }
    }

    private boolean isHourlyDataFetched(PostalCoordinate postalCoordinate) {
        return hourlyWeatherRepository.existsByPostalCoordinate(postalCoordinate);
    }

    private URI buildHourlyWeatherUri(PostalCoordinate postalCoordinate, LocalDate beginning, LocalDate end) {
        return uriComponentsBuilder
                .buildAndExpand(
                        weatherSourceApiProperties.getApiKey(),
                        postalCoordinate.getPostalCode(),
                        postalCoordinate.getCountryCode(),
                        beginning,
                        end,
                        weatherSourceApiProperties.getHourlyFields(),
                        weatherSourceApiProperties.getPeriodHour())
                .encode()
                .toUri();
    }

    private List<HourlyWeather> getHourlyWeathersResponse(URI uri) {
        ResponseEntity<List<HourlyWeather>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}

package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.service.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySource(value = "classpath:secrets.properties")
public class DefaultApiService implements ApiService {

    // sample URL: https://api.weathersource.com/v1/{{api_key}}/postal_codes/{{postal_code}},{{country}}/history.json?timestamp_between={{beginning}},{{end}}&fields={{fields}}

    private static final String HOST = "api.weathersource.com";
    private static final String PATH = "/v1/{api_key}/postal_codes/{postal_code},{country}/history.json";
    private static final String TIMESTAMP_QUERY = "timestamp_between={beginning},{end}";
    private static final String FIELDS_QUERY = "fields={fields}";
    private static final String DAILY_FIELDS = "tempAvg,feelsLikeAvg,heatIndexAvg,mslPresAvg,precip,snowfall,cldCvrAvg,windSpdAvg,relHumAvg";
    private static final String HOURLY_FIELDS = "temp,feelsLike,heatIndex,mslPres,precip,snowfall,cldCvr,windSpd,relHum&period=hour";

    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    @Value("${api.weathersource.key}")
    private String API_KEY;

    public DefaultApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(HOST)
                .path(PATH)
                .query(TIMESTAMP_QUERY)
                .query(FIELDS_QUERY);
    }

    public List<DailyWeather> getDaysOfYear(String postalCode, String countryCode, int year) {
        URI uri = buildDaysUri(postalCode, countryCode, year);
        ResponseEntity<DailyWeather[]> response = restTemplate.getForEntity(uri, DailyWeather[].class);
        return Arrays.asList(response.getBody());
    }

    public List<HourlyWeather> getHoursOfYear(String postalCode, String countryCode, int year) {
        URI uri = buildHoursUri(postalCode, countryCode, year);
        ResponseEntity<HourlyWeather[]> response = restTemplate.getForEntity(uri, HourlyWeather[].class);
        return Arrays.asList(response.getBody());
    }

    private URI buildDaysUri(String postalCode, String countryCode, int year) {
        LocalDate beginning = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return uriComponentsBuilder.buildAndExpand(API_KEY, postalCode, countryCode, beginning, end, DAILY_FIELDS).encode().toUri();
    }

    private URI buildHoursUri(String postalCode, String countryCode, int year) {
        LocalDate beginning = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return uriComponentsBuilder.buildAndExpand(API_KEY, postalCode, countryCode, beginning, end, HOURLY_FIELDS).encode().toUri();
    }
}

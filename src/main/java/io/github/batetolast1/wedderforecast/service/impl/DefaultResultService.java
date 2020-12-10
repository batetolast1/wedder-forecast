package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.dto.RequestFormSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.mapper.result.SimpleResultMapper;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.SimpleResult;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.repository.results.SimpleResultRepository;
import io.github.batetolast1.wedderforecast.service.LocationService;
import io.github.batetolast1.wedderforecast.service.ResultService;
import io.github.batetolast1.wedderforecast.service.WeatherService;
import io.github.batetolast1.wedderforecast.service.WeatherSourceApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class DefaultResultService implements ResultService {

    private final LocationService locationService;
    private final WeatherService weatherService;
    private final WeatherSourceApiService weatherSourceApiService;

    private final SimpleResultRepository simpleResultRepository;

    private final SimpleResultMapper simpleResultMapper;

    public ResponseSimpleResultDto getSimpleSearchResultFromForm(RequestFormSimpleResultDto requestFormSimpleResultDto) {
        Location location = locationService.getLocationByPostalCodeAndCountryCode(requestFormSimpleResultDto.getLocationDto());
        LocalDate localDate = LocalDate.of(requestFormSimpleResultDto.getYear(), requestFormSimpleResultDto.getMonth(), requestFormSimpleResultDto.getDay());

        return getResponseSimpleResultDto(location, localDate);
    }

    public ResponseSimpleResultDto getSimpleSearchResultFromGoogleMaps(RequestGoogleMapsDailyResultDto requestGoogleMapsDailyResultDto) {
        Location location = locationService.getLocationByPlaceId(requestGoogleMapsDailyResultDto.getLocationDto());
        LocalDate localDate = requestGoogleMapsDailyResultDto.getLocalDate();

        return getResponseSimpleResultDto(location, localDate);
    }

    private ResponseSimpleResultDto getResponseSimpleResultDto(Location location, LocalDate localDate) {
        Optional<SimpleResult> optionalSimpleResult = simpleResultRepository.findByLocationAndLocalDateTime(location, localDate.atStartOfDay());
        if (optionalSimpleResult.isPresent()) {
            return simpleResultMapper.toResponseSimpleResultDto(optionalSimpleResult.get());
        }

        return getNewResponseSimpleResultDto(location, localDate);
    }

    private ResponseSimpleResultDto getNewResponseSimpleResultDto(Location location, LocalDate localDate) {
        weatherSourceApiService.getDailyWeathers(location, localDate);

        PredictedDailyWeather predictedDailyWeather = weatherService.predictDailyWeather(location, localDate);

        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setLocation(location);
        simpleResult.setLocalDateTime(localDate.atStartOfDay());
        simpleResult.setPredictedDailyWeather(predictedDailyWeather);
        simpleResultRepository.save(simpleResult);

        return simpleResultMapper.toResponseSimpleResultDto(simpleResult);
    }
}

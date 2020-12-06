package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.dto.RequestFormSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.SimpleResult;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import io.github.batetolast1.wedderforecast.model.repository.results.SimpleResultRepository;
import io.github.batetolast1.wedderforecast.service.LocationService;
import io.github.batetolast1.wedderforecast.service.ResultService;
import io.github.batetolast1.wedderforecast.service.WeatherService;
import io.github.batetolast1.wedderforecast.service.WeatherSourceApiService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    public ResponseSimpleResultDto getSimpleSearchResultFromForm(RequestFormSimpleResultDto requestFormSimpleResultDto) {
        Location location = locationService.getLocationByPostalCodeAndCountryCode(requestFormSimpleResultDto.getLocationDto());
        LocalDate localDate = LocalDate.of(requestFormSimpleResultDto.getYear(), requestFormSimpleResultDto.getMonth(), requestFormSimpleResultDto.getDay());

        return getResponseSimpleResultDto(location, localDate);
    }

    public ResponseSimpleResultDto getSimpleSearchResultFromGoogleMaps(RequestGoogleMapsSimpleResultDto requestGoogleMapsSimpleResultDto) {
        Location location = locationService.getLocationByPlaceId(requestGoogleMapsSimpleResultDto.getLocationDto());
        LocalDate localDate = requestGoogleMapsSimpleResultDto.getLocalDate();

        return getResponseSimpleResultDto(location, localDate);
    }

    private ResponseSimpleResultDto getResponseSimpleResultDto(Location location, LocalDate localDate) {
        Optional<SimpleResult> optionalSimpleResult = simpleResultRepository.findByLocationAndLocalDate(location, localDate);
        if (optionalSimpleResult.isPresent()) {
            return modelMapper.map(optionalSimpleResult.get(), ResponseSimpleResultDto.class);
        }

        return getNewResponseSimpleResultDto(location, localDate);
    }

    private ResponseSimpleResultDto getNewResponseSimpleResultDto(Location location, LocalDate localDate) {
        weatherSourceApiService.getDailyWeathers(location, localDate);

        PredictedDailyWeather predictedDailyWeather = weatherService.predictDailyWeather(location, localDate);

        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setLocation(location);
        simpleResult.setLocalDate(localDate);
        simpleResult.setPredictedDailyWeather(predictedDailyWeather);
        simpleResultRepository.save(simpleResult);

        return modelMapper.map(simpleResult, ResponseSimpleResultDto.class);
    }
}

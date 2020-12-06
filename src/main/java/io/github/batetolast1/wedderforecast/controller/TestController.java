package io.github.batetolast1.wedderforecast.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.dto.RequestFormSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsSimpleResultDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.repository.location.LocationRepository;
import io.github.batetolast1.wedderforecast.service.LocationService;
import io.github.batetolast1.wedderforecast.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")

@RequiredArgsConstructor
public class TestController {

    private final ResultService resultService;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    @GetMapping("/simple-result")
    @ResponseBody
    public ResponseSimpleResultDto getTestSimpleSearchResult() {
        RequestFormSimpleResultDto requestFormSimpleResultDto = new RequestFormSimpleResultDto();
        LocationDto locationDto = new LocationDto();
        locationDto.setPostalCode("61-054");
        locationDto.setCountryCode("PL");
        requestFormSimpleResultDto.setLocationDto(locationDto);
        requestFormSimpleResultDto.setDay(5);
        requestFormSimpleResultDto.setMonth(6);
        requestFormSimpleResultDto.setYear(2021);
        return resultService.getSimpleSearchResultFromForm(requestFormSimpleResultDto);
    }

    @GetMapping("/deserialize-daily-weather")
    @ResponseBody
    public List<DailyWeather> deserializeSampleDailyWeather() throws JsonProcessingException {
        String json = """
                [
                    {
                        "timestamp": "2020-06-05T00:00:00+02:00",
                        "cldCvrAvg": 15,
                        "feelsLikeAvg": 72.2,
                        "heatIndexAvg": 72.2,
                        "mslPresAvg": 1009.7,
                        "precip": 0.04,
                        "relHumAvg": 66.1,
                        "snowfall": 0,
                        "tempAvg": 71.7,
                        "windSpdAvg": 9.2
                    }
                ]                    
                """;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {});
    }

    @GetMapping("/find-location-by-postal-code-and-country-code")
    @ResponseBody
    public Location findLocationByPostalCodeAndCountryCode() {
        LocationDto locationDto = new LocationDto();
        locationDto.setPostalCode("61-054");
        locationDto.setCountryCode("PL");
        return locationRepository.findFirstByPostalCodeAndCountryCode(locationDto.getPostalCode(), locationDto.getCountryCode())
                .orElse(null);
    }

    @GetMapping("/get-location")
    @ResponseBody
    public Location getLocation() {
        LocationDto locationDto = new LocationDto();
        locationDto.setPostalCode("61-054");
        locationDto.setCountryCode("PL");
        return locationService.getLocationByPostalCodeAndCountryCode(locationDto);
    }

    @GetMapping("/google-maps")
    public ModelAndView showMap() {
        ModelAndView modelAndView = new ModelAndView("test/google-maps");
        modelAndView.addObject("requestGoogleMapsSimpleResultDto", new RequestGoogleMapsSimpleResultDto());
        return modelAndView;
    }

    @PostMapping("/google-maps")
    @ResponseBody
    public ResponseSimpleResultDto getTestSimpleSearchResultGoogleMaps(@ModelAttribute RequestGoogleMapsSimpleResultDto requestGoogleMapsSimpleResultDto) {
        return resultService.getSimpleSearchResultFromGoogleMaps(requestGoogleMapsSimpleResultDto);
    }
}

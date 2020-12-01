package io.github.batetolast1.wedderforecast.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.batetolast1.wedderforecast.dto.LocationDto;
import io.github.batetolast1.wedderforecast.dto.RequestSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")

@RequiredArgsConstructor
public class TestController {

    private final ResultService resultService;

    @GetMapping("/simple-result")
    @ResponseBody
    public ResponseSimpleResultDto getTestSimpleSearchResult() {
        var requestSimpleSearchResultDto = new RequestSimpleResultDto();
        LocationDto locationDto = new LocationDto();
        locationDto.setPostalCode("61-054");
        locationDto.setCountryCode("PL");
        requestSimpleSearchResultDto.setLocationDto(locationDto);
        requestSimpleSearchResultDto.setDay(5);
        requestSimpleSearchResultDto.setMonth(6);
        requestSimpleSearchResultDto.setYear(2021);

        return resultService.getSimpleSearchResult(requestSimpleSearchResultDto);
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
}

package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.DailyResultMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.HourlyResultMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.DailyResultDto;
import io.github.batetolast1.wedderforecast.dto.model.result.HourlyResultDto;
import io.github.batetolast1.wedderforecast.dto.model.result.RequestDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.model.result.RequestHourlyResultDto;
import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.results.HourlyResult;
import io.github.batetolast1.wedderforecast.service.api.WeatherSourceApiService;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import io.github.batetolast1.wedderforecast.service.result.DailyResultService;
import io.github.batetolast1.wedderforecast.service.result.HourlyResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final DailyResultService dailyResultService;
    private final HourlyResultService hourlyResultService;
    private final LocationService locationService;
    private final WeatherSourceApiService weatherSourceApiService;

    private final LocationMapper locationMapper;
    private final DailyResultMapper dailyResultMapper;
    private final HourlyResultMapper hourlyResultMapper;

    @GetMapping(value = {"/", "/dashboard"})
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView("user/dashboard");
        List<DailyResult> dailyResults = dailyResultService.getLatestDailyResults();
        List<DailyResultDto> dailyResultDtos = dailyResults.stream()
                .map(dailyResultMapper::toDailyResultDto)
                .collect(Collectors.toList());
        modelAndView.addObject("dailyResultDtos", dailyResultDtos);

        List<HourlyResult> hourlyResults = hourlyResultService.getLatestHourlyResults();
        List<HourlyResultDto> hourlyResultDtos = hourlyResults.stream()
                .map(hourlyResultMapper::toHourlyResultDto)
                .collect(Collectors.toList());
        modelAndView.addObject("hourlyResultDtos", hourlyResultDtos);
        return modelAndView;
    }

    @GetMapping("/daily-result-form")
    public ModelAndView getDailyResultForm() {
        ModelAndView modelAndView = new ModelAndView("user/daily-result-form");
        modelAndView.addObject("requestDailyResultDto", new RequestDailyResultDto());
        return modelAndView;
    }

    @PostMapping("/daily-result-form")
    public ModelAndView processDailyResultForm(@ModelAttribute RequestDailyResultDto requestDailyResultDto) {
        LocalDateTime localDateTime = requestDailyResultDto.getLocalDate().atStartOfDay();
        Location location = locationService.getPersistedLocation(locationMapper.toLocation(requestDailyResultDto.getLocationDto()));
        log.debug("Processing request for Daily Result for date: {}, location {}", localDateTime, location.getName());

        weatherSourceApiService.fetchDailyWeathers(location.getPostalCoordinate());
        log.debug("Daily weathers fetched.");

        DailyResult dailyResult = dailyResultService.getDailyResult(location, localDateTime);
        log.debug("Daily Result obtained, redirecting to \"/user/daily-result-details/\"");
        return new ModelAndView("redirect:/user/daily-result-details/" + dailyResult.getId());
    }

    @GetMapping("/daily-result-details/{id}")
    public ModelAndView getDailyResultById(@PathVariable Long id) {
        DailyResult dailyResult = dailyResultService.getDailyResult(id);
        if (dailyResult != null) {
            ModelAndView modelAndView = new ModelAndView("user/daily-result-details");
            DailyResultDto dailyResultDto = dailyResultMapper.toDailyResultDto(dailyResult);
            modelAndView.addObject("dailyResultDto", dailyResultDto);
            return modelAndView;
        } else {
            return new ModelAndView("error/error-404");
        }
    }

    @GetMapping("/daily-results")
    public ModelAndView getDailyResults() {
        ModelAndView modelAndView = new ModelAndView("user/daily-results");
        List<DailyResult> dailyResults = dailyResultService.getAllDailyResults();
        List<DailyResultDto> dailyResultDtos = dailyResults.stream()
                .map(dailyResultMapper::toDailyResultDto)
                .collect(Collectors.toList());
        modelAndView.addObject("dailyResultDtos", dailyResultDtos);
        return modelAndView;
    }

    @GetMapping("/hourly-result-form")
    public ModelAndView getHourlyResultForm() {
        ModelAndView modelAndView = new ModelAndView("user/hourly-result-form");
        modelAndView.addObject("requestHourlyResultDto", new RequestHourlyResultDto());
        return modelAndView;
    }

    @PostMapping("/hourly-result-form")
    public ModelAndView processHourlyResultForm(@ModelAttribute RequestHourlyResultDto requestHourlyResultDto) {
        LocalDateTime localDateTime = requestHourlyResultDto.getLocalDateTime();
        Location location = locationService.getPersistedLocation(locationMapper.toLocation(requestHourlyResultDto.getLocationDto()));
        log.debug("Processing request for Hourly Result for date: {}, location {}", localDateTime, location.getName());

        weatherSourceApiService.fetchHourlyWeathers(location.getPostalCoordinate());
        log.debug("Hourly weathers fetched.");

        HourlyResult hourlyResult = hourlyResultService.getHourlyResult(location, localDateTime);
        log.debug("Hourly Result obtained, redirecting to \"/user/hourly-result-details/\"");
        return new ModelAndView("redirect:/user/hourly-result-details/" + hourlyResult.getId());
    }

    @GetMapping("/hourly-result-details/{id}")
    public ModelAndView getHourlyResultById(@PathVariable Long id) {
        HourlyResult hourlyResult = hourlyResultService.getHourlyResult(id);
        if (hourlyResult != null) {
            ModelAndView modelAndView = new ModelAndView("user/hourly-result-details");
            HourlyResultDto hourlyResultDto = hourlyResultMapper.toHourlyResultDto(hourlyResult);
            modelAndView.addObject("hourlyResultDto", hourlyResultDto);
            return modelAndView;
        } else {
            return new ModelAndView("error/error-404");
        }
    }

    @GetMapping("/hourly-results")
    public ModelAndView getHourlyResults() {
        ModelAndView modelAndView = new ModelAndView("user/hourly-results");
        List<HourlyResult> hourlyResults = hourlyResultService.getAllHourlyResults();
        List<HourlyResultDto> hourlyResultDtos = hourlyResults.stream()
                .map(hourlyResultMapper::toHourlyResultDto)
                .collect(Collectors.toList());
        modelAndView.addObject("hourlyResultDtos", hourlyResultDtos);
        return modelAndView;
    }
}

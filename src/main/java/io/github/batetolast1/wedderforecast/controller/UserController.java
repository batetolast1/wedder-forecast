package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.RequestHourlyResultDto;
import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.DailyResultMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.HourlyResultMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.DailyResultDto;
import io.github.batetolast1.wedderforecast.dto.model.result.HourlyResultDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.entity.results.HourlyResult;
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

    private final LocationMapper locationMapper;
    private final DailyResultMapper dailyResultMapper;
    private final HourlyResultMapper hourlyResultMapper;

    @GetMapping("/dashboard")
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
        modelAndView.addObject("requestGoogleMapsDailyResultDto", new RequestGoogleMapsDailyResultDto());
        return modelAndView;
    }

    @PostMapping("/daily-result-form")
    public ModelAndView processDailyResultForm(@ModelAttribute RequestGoogleMapsDailyResultDto requestGoogleMapsDailyResultDto) {
        Location location = locationMapper.toLocation(requestGoogleMapsDailyResultDto.getLocationDto());
        LocalDateTime localDateTime = requestGoogleMapsDailyResultDto.getLocalDate().atStartOfDay();
        DailyResult dailyResult = dailyResultService.getDailyResult(location, localDateTime);
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
            return new ModelAndView("error/error");
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
        Location location = locationMapper.toLocation(requestHourlyResultDto.getLocationDto());
        LocalDateTime localDateTime = requestHourlyResultDto.getLocalDateTime();
        HourlyResult hourlyResult = hourlyResultService.getHourlyResult(location, localDateTime);
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
            return new ModelAndView("error/error");
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

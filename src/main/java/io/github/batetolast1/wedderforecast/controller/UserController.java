package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.DailyResultMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.DailyResultDto;
import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import io.github.batetolast1.wedderforecast.service.result.DailyResultService;
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

    private final LocationMapper locationMapper;
    private final DailyResultMapper dailyResultMapper;

    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView("user/dashboard");
        List<DailyResult> dailyResults = dailyResultService.getAllDailyResults();
        List<DailyResultDto> dailyResultDtos = dailyResults.stream()
                .map(dailyResultMapper::toDailyResultDto)
                .collect(Collectors.toList());
        modelAndView.addObject("dailyResultDtos", dailyResultDtos);
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
}

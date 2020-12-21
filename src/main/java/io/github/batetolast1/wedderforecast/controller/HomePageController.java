package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.mapper.location.LocationMapper;
import io.github.batetolast1.wedderforecast.dto.mapper.result.SimpleDailyResultMapper;
import io.github.batetolast1.wedderforecast.dto.model.result.RequestDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.model.result.SimpleDailyResultDto;
import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.SimpleDailyResult;
import io.github.batetolast1.wedderforecast.service.api.WeatherSourceApiService;
import io.github.batetolast1.wedderforecast.service.location.LocationService;
import io.github.batetolast1.wedderforecast.service.result.SimpleDailyResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import static io.github.batetolast1.wedderforecast.util.SecurityUtils.isUserLoggedIn;

@Controller
@RequestMapping("/")

@Log4j2
@RequiredArgsConstructor
public class HomePageController {

    private final LocationService locationService;
    private final WeatherSourceApiService weatherSourceApiService;
    private final SimpleDailyResultService simpleDailyResultService;

    private final LocationMapper locationMapper;
    private final SimpleDailyResultMapper simpleDailyResultMapper;

    @GetMapping("/")
    public ModelAndView getHomePage() {
        if (isUserLoggedIn()) {
            log.debug("User logged in - redirecting to \"/user/dashboard\"");
            return new ModelAndView("redirect:/user/dashboard");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("requestDailyResultDto", new RequestDailyResultDto());
        return modelAndView;
    }

    @GetMapping("/simple-result")
    public ModelAndView redirectToHomePage() {
        log.debug("No Simple Daily Result id provided, redirecting to \"/\"");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/simple-result/{id}")
    public ModelAndView displaySimpleDailyResult(@PathVariable Long id) {
        SimpleDailyResult simpleDailyResult = simpleDailyResultService.getSimpleDailyResult(id);
        if (simpleDailyResult == null) {
            log.debug("Simple Daily Result not found for id: {}, redirecting to \"/\"", id);
            return new ModelAndView("redirect:/");
        }

        SimpleDailyResultDto simpleDailyResultDto = simpleDailyResultMapper.toSimpleDailyResultDto(simpleDailyResult);
        ModelAndView modelAndView = new ModelAndView("simple-result");
        modelAndView.addObject("simpleDailyResultDto", simpleDailyResultDto);

        log.debug("Simple Daily Result found for id: {}, displaying \"/simple-result\"", id);
        return modelAndView;
    }

    @PostMapping("/simple-result")
    public ModelAndView processDailyResultForm(RequestDailyResultDto requestDailyResultDto) {
        LocalDateTime localDateTime = requestDailyResultDto.getLocalDate().atStartOfDay();
        Location location = locationService.getPersistedLocation(locationMapper.toLocation(requestDailyResultDto.getLocationDto()));
        log.debug("Processing request for Simple Daily Result for date: {}, location {}", localDateTime, location.getName());

        weatherSourceApiService.fetchDailyWeathers(location.getPostalCoordinate());
        log.debug("Daily weathers fetched.");

        SimpleDailyResult simpleDailyResult = simpleDailyResultService.getSimpleDailyResult(location, localDateTime);
        log.debug("Simple Daily Result obtained, redirecting to \"/simple-result\"");
        return new ModelAndView("redirect:/simple-result/" + simpleDailyResult.getId());
    }
}

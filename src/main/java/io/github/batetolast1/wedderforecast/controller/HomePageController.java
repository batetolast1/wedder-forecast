package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.service.result.SimpleResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static io.github.batetolast1.wedderforecast.util.SecurityUtils.isUserLoggedIn;

@Controller
@RequestMapping("/")

@Log4j2
@RequiredArgsConstructor
public class HomePageController {

    private final SimpleResultService simpleResultService;

    @GetMapping("/")
    public ModelAndView getHomePage() {
        if (isUserLoggedIn()) {
            return new ModelAndView("redirect:/user/dashboard");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("requestGoogleMapsDailyResultDto", new RequestGoogleMapsDailyResultDto());
        return modelAndView;
    }

    @PostMapping("/simple-result")
    public RedirectView processSimpleResultForm(RequestGoogleMapsDailyResultDto requestGoogleMapsDailyResultDto,
                                                RedirectAttributes redirectAttributes) {
        ResponseSimpleResultDto responseSimpleResultDto = simpleResultService.getSimpleSearchResultFromGoogleMaps(requestGoogleMapsDailyResultDto);
        redirectAttributes.addFlashAttribute("responseSimpleResultDto", responseSimpleResultDto);
        return new RedirectView("/simple-result");
    }

    @GetMapping("/simple-result")
    public ModelAndView displaySimpleResult(ResponseSimpleResultDto responseSimpleResultDto) {
        if (responseSimpleResultDto.getLocalDateTime() == null) {
            log.debug("responseSimpleResultDto is not populated by RedirectAttributes, redirecting to \"/\"");
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("simple-result");
        modelAndView.addObject("responseSimpleResultDto", responseSimpleResultDto);
        return modelAndView;
    }
}

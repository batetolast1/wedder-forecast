package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")

@Log4j2
@RequiredArgsConstructor
public class HomePageController {

    private final ResultService resultService;

    @GetMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("requestGoogleMapsSimpleResultDto", new RequestGoogleMapsSimpleResultDto());
        return modelAndView;
    }

    @PostMapping("/simple-result")
    public RedirectView processSimpleResultForm(RequestGoogleMapsSimpleResultDto requestGoogleMapsSimpleResultDto,
                                                RedirectAttributes redirectAttributes) {
        ResponseSimpleResultDto responseSimpleResultDto = resultService.getSimpleSearchResultFromGoogleMaps(requestGoogleMapsSimpleResultDto);
        redirectAttributes.addFlashAttribute("responseSimpleResultDto", responseSimpleResultDto);
        return new RedirectView("/simple-result");
    }

    @GetMapping("/simple-result")
    public ModelAndView displaySimpleResult(ResponseSimpleResultDto responseSimpleResultDto) {
        if (responseSimpleResultDto.getLocalDate() == null) {
            log.debug("responseSimpleResultDto is not populated by RedirectAttributes, redirecting to \"/\"");
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("simple-result");
        modelAndView.addObject("responseSimpleResultDto", responseSimpleResultDto);
        return modelAndView;
    }
}

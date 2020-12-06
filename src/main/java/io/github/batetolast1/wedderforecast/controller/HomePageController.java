package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RequestSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")

@RequiredArgsConstructor
public class HomePageController {

    private final ResultService resultService;

    @GetMapping
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("requestSimpleResultDto", new RequestSimpleResultDto());
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView processSimpleResultForm(RequestSimpleResultDto requestSimpleResultDto) {
        ModelAndView modelAndView = new ModelAndView("index");
        ResponseSimpleResultDto responseSimpleResultDto = resultService.getSimpleSearchResult(requestSimpleResultDto);
        modelAndView.addObject("responseSimpleResultDto", responseSimpleResultDto);
        return modelAndView;
    }
}

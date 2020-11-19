package io.github.batetolast1.wedderforecast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }
}

package io.github.batetolast1.wedderforecast.controller;

import io.github.batetolast1.wedderforecast.dto.RegistrationDataDto;
import io.github.batetolast1.wedderforecast.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")

@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ModelAndView prepareRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("register/register");
        modelAndView.addObject("registrationDataDto", new RegistrationDataDto());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView processRegistrationPage(@Valid RegistrationDataDto registrationDataDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register/register");
        }

        registrationService.register(registrationDataDto);
        return new ModelAndView("redirect:/login");
    }
}

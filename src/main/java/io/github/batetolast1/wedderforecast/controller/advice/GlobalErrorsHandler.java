package io.github.batetolast1.wedderforecast.controller.advice;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller

@Log4j2
public class GlobalErrorsHandler implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorDateTime(LocalDateTime.now());
        errorInfo.setRequestMethod(request.getMethod());
        errorInfo.setRequestURI(request.getRequestURI());
        errorInfo.setResponseStatus(response.getStatus());
        log.info("Error info: {}", errorInfo);

        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorInfo", errorInfo);
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @Data
    public static class ErrorInfo {

        LocalDateTime errorDateTime;
        String requestMethod;
        String requestURI;
        int responseStatus;
    }
}

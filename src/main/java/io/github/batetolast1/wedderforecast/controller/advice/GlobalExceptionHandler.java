package io.github.batetolast1.wedderforecast.controller.advice;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = Controller.class)

@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(Exception exception) {
        log.warn("Exception caught: {}", exception.getMessage(), exception);
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setExceptionDateTime(LocalDateTime.now());
        exceptionInfo.setMessage(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView("error/error-500");
        modelAndView.addObject("exceptionInfo", exceptionInfo);
        return modelAndView;
    }

    @Data
    public static class ExceptionInfo {

        private LocalDateTime exceptionDateTime;
        private String message;
    }
}

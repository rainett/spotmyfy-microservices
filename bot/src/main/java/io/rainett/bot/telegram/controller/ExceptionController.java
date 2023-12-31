package io.rainett.bot.telegram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException ex) {
        log.error("Received a runtime exception", ex);
    }

}

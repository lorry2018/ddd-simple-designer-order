package com.screw.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMessage errorHandlerForUncaughtException(Exception exception) {
        logger.error("Unhandled exception occurs.", exception);

        return ResultMessage.failed(exception.getMessage());
    }

}
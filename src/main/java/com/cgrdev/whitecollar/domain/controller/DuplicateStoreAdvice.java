package com.cgrdev.whitecollar.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class DuplicateStoreAdvice {

    @ResponseBody
    @ExceptionHandler(DuplicateSoreException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String storeNotFoundHandler(DuplicateSoreException e){
        return e.getMessage();
    }
}

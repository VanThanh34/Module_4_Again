package com.example.baitap.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(ExcessPlayerException.class)
    public String handleExcessPlayerException(){
        return "excess_player_exception";
    }
}

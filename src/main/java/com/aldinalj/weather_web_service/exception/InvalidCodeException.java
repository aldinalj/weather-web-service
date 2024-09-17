package com.aldinalj.weather_web_service.exception;

public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException () {
        super("Code not allowed. Please use one of these instead: 2, 5, 6, 7, 8.");
    }
}

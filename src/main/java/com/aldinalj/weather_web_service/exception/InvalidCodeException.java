package com.aldinalj.weather_web_service.exception;

public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException () {
        super("Invalid code. Please use one of these instead: 200, 500, 600, 700, 800.");
    }
}

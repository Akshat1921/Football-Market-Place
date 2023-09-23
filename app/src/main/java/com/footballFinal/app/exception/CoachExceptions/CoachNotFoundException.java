package com.footballFinal.app.exception.CoachExceptions;

public class CoachNotFoundException extends RuntimeException {
    public CoachNotFoundException(String s) {
        super(s);
    }
}

package com.footballFinal.app.exception.CoachExceptions;

public class CoachesNotFoundException extends RuntimeException {
    public CoachesNotFoundException(String s) {
        super(s);
    }
}

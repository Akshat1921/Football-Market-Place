package com.footballFinal.app.exception.TeamExceptions;

public class TeamsNotFoundException extends RuntimeException {
    public TeamsNotFoundException(String message) {
        super(message);
    }
}

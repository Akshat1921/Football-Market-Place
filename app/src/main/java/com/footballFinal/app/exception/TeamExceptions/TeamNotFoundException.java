package com.footballFinal.app.exception.TeamExceptions;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(String s) {
        super(s);
    }
}

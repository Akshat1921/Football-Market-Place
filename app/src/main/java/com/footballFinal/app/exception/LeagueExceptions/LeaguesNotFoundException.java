package com.footballFinal.app.exception.LeagueExceptions;

public class LeaguesNotFoundException extends RuntimeException {
    public LeaguesNotFoundException(String message) {
        super(message);
    }
}

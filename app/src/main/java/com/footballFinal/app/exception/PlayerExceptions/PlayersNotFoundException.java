package com.footballFinal.app.exception.PlayerExceptions;

public class PlayersNotFoundException extends RuntimeException {
    public PlayersNotFoundException(String s) {
        super(s);
    }
}

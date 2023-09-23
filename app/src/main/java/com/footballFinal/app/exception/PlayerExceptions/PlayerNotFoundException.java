package com.footballFinal.app.exception.PlayerExceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String s) {
        super(s);
    }
}

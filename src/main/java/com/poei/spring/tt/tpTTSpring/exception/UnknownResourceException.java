package com.poei.spring.tt.tpTTSpring.exception;

public class UnknownResourceException extends RuntimeException {

    public UnknownResourceException() {
        super("Ressource inconnue.");
    }

    public UnknownResourceException(String message) {
        super(message);
    }
}

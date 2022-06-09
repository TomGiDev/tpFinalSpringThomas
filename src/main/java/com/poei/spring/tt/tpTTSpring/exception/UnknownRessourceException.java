package com.poei.spring.tt.tpTTSpring.exception;

public class UnknownRessourceException extends RuntimeException {

    public UnknownRessourceException() {
        super("Ressource inconnue.");
    }

    public UnknownRessourceException(String message) {
        super(message);
    }
}

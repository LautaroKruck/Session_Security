package com.es.seguridadsession.exception;

public class ConflictException extends RuntimeException {

    private static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}


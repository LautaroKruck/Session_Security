package com.es.seguridadsession.exception;


public class UnauthorizedException extends RuntimeException{

    private static final String DESCRIPCION = "Unathorized (403)";

    public UnauthorizedException(String message) {
        super(DESCRIPCION + ". " + message);
    }


}
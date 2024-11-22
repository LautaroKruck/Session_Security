package com.es.seguridadsession.utils;

import java.util.UUID;

/**
 * CLASE ENCARGADA DE GENERAR TOKENS
 */
public class TokenUtil {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}

package com.es.seguridadsession.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Genera un salt aleatorio de 16 bytes
     * @return salt en Base64
     */
    public static String generateSalt() {
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    /**
     * Hashea una contraseña con SHA-256 y un salt
     * @param password Contraseña a hashear
     * @param salt Salt en Base64
     * @return Hash de la contraseña + salt
     */
    public static String encryptPassword(String password, String salt) {
        StringBuilder passwordHashed = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = salt + password;
            byte[] hashBytes = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                passwordHashed.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el hash", e);
        }

        return passwordHashed.toString();
    }
}

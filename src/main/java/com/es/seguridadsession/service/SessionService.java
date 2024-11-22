package com.es.seguridadsession.service;

import com.es.seguridadsession.exception.UnauthorizedException;
import com.es.seguridadsession.model.Session;
import com.es.seguridadsession.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public boolean checkToken(String token) {

        // Comprobamos que el token existe y es válido
        Session s = sessionRepository
                .findByToken(token)
                .orElseThrow(() -> new UnauthorizedException("Token no encontrado."));

        // SI ESTOY EN ESTE PUNTO, es que ha encontrado el TOKEN
        // Compruebo si la fecha es correcta
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(s.getExpirationDate())) {
            // Si el token ha expirado, lanzo una excepción personalizada
            throw new UnauthorizedException("El token ha expirado.");
        }

        // Si no ha expirado, devuelvo true
        return true;
    }

    public boolean isValidSession(String token) {

        Session session = sessionRepository.findByToken(token)
                .orElseThrow(() -> new UnauthorizedException("Sesión no encontrada."));
        return session.getExpirationDate().isAfter(LocalDateTime.now());
    }

    public boolean hasRole(String token, String role) {

        Session session = sessionRepository.findByToken(token)
                .orElseThrow(() -> new UnauthorizedException("Sesión no encontrada."));
        return session.getUsuario().getRol().equalsIgnoreCase(role);
    }

}

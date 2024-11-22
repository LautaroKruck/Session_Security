package com.es.seguridadsession.controller;

import com.es.seguridadsession.dto.UsuarioDTO;
import com.es.seguridadsession.dto.UsuarioInsertDTO;

import com.es.seguridadsession.exception.*;
import com.es.seguridadsession.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint: POST /usuarios/login
     * Permite autenticar a un usuario y devuelve un token de sesión si las credenciales son válidas.
     *
     * @param userLogin Objeto UsuarioDTO con nombre y password.
     * @return Token de sesión si las credenciales son válidas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO userLogin) {
        try {
            String token = usuarioService.login(userLogin);
            return ResponseEntity.ok(token); // Devuelve el token como respuesta
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el servidor.");
        }
    }

    /**
     * Endpoint: POST /usuarios
     * Permite registrar un nuevo usuario en el sistema.
     *
     * @param nuevoUser Objeto UsuarioInsertDTO con los datos del usuario a registrar.
     * @return Usuario registrado en formato DTO.
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody UsuarioInsertDTO nuevoUser) {
        try {
            UsuarioDTO usuarioRegistrado = usuarioService.insert(nuevoUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el servidor.");
        }
    }
}
package com.es.seguridadsession.service;

import com.es.seguridadsession.dto.UsuarioDTO;
import com.es.seguridadsession.dto.UsuarioInsertDTO;
import com.es.seguridadsession.exception.NotFoundException;
import com.es.seguridadsession.model.Session;
import com.es.seguridadsession.model.Usuario;
import com.es.seguridadsession.repository.SessionRepository;
import com.es.seguridadsession.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario saveUsuario(UsuarioInsertDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRol(usuarioDTO.getRol());
        return usuarioRepository.save(usuario);
    }


    public String login(UsuarioDTO userLogin) {

        // Comprobar si user y pass son correctos -> obtener de la BDD el usuario
        String nombreUser = userLogin.getNombre();
        String passUser = userLogin.getPassword();

        Usuario u = usuarioRepository.findByNombre(userLogin.getNombre())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(userLogin.getPassword(), u.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        // Si coincide -> Insertar una sesión
        // Genero un TOKEN
        String token = UUID.randomUUID().toString(); // Esto genera un token aleatorio
        System.out.println("Token generado: "+token);
        // Almaceno la Session en la base de datos
        Session s = new Session();
        s.setToken(token);
        s.setUsuario(u);
//        s.setExpirationDate(
//                LocalDateTime.of(
//                        LocalDate.of(2024, 11, 20),
//                        LocalTime.now()
//                ));
        s.setExpirationDate(
                LocalDateTime.now().plusMinutes(1)
        );

        sessionRepository.save(s);

        return token;

    }
}

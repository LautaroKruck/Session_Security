package com.es.seguridadsession.controller;

import com.es.seguridadsession.dto.ProductoDTO;
import com.es.seguridadsession.exception.UnauthorizedException;
import com.es.seguridadsession.service.ProductoService;
import com.es.seguridadsession.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private SessionService sessionService;

    /**
     * GET PRODUCTO POR SU ID.
     * Endpoint accesible para usuarios con roles USER o ADMIN.
     *
     * @param id      ID del producto.
     * @param token   Token de sesión del usuario.
     * @return ProductoDTO con los datos del producto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getById(
            @PathVariable String id,
            @RequestHeader("Authorization") String token
    ) {
        // Verificar la sesión
        if (!sessionService.isValidSession(token)) {
            throw new UnauthorizedException("Sesión no válida o expirada.");
        }

        // Obtener el producto
        ProductoDTO producto = productoService.getById(id);
        return ResponseEntity.ok(producto);
    }

    /**
     * INSERTAR PRODUCTO.
     * Endpoint accesible solo para usuarios con rol ADMIN.
     *
     * @param productoDTO Datos del producto a insertar.
     * @param token       Token de sesión del usuario.
     * @return ProductoDTO con los datos del producto recién creado.
     */
    @PostMapping("/")
    public ResponseEntity<ProductoDTO> insert(
            @RequestBody ProductoDTO productoDTO,
            @RequestHeader("Authorization") String token
    ) {
        // Verificar la sesión y rol de ADMIN
        if (!sessionService.isValidSession(token)) {
            throw new UnauthorizedException("Sesión no válida o expirada.");
        }

        if (!sessionService.hasRole(token, "ADMIN")) {
            throw new UnauthorizedException("Permiso denegado. Solo los administradores pueden realizar esta acción.");
        }

        // Insertar el producto
        ProductoDTO productoCreado = productoService.insert(productoDTO);
        return ResponseEntity.status(201).body(productoCreado);
    }
}

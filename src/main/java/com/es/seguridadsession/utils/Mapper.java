package com.es.seguridadsession.utils;

import com.es.seguridadsession.model.Usuario;
import com.es.seguridadsession.dto.UsuarioDTO;
import com.es.seguridadsession.model.Producto;
import com.es.seguridadsession.dto.ProductoDTO;

public class Mapper {

    // Método para mapear Usuario a UsuarioDTO
    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getNombre(),
                usuario.getPassword()
        );
    }

    // Método para mapear UsuarioDTO a Usuario
    public static Usuario toUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        return new Usuario(
                usuarioDTO.getNombre(),
                usuarioDTO.getPassword()
        );
    }

    // Método para mapear Producto a ProductoDTO
    public static ProductoDTO toProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoDTO(
                producto.getNombre(),
                producto.getStock(),
                producto.isPrecio()
        );
    }

    // Método para mapear ProductoDTO a Producto
    public static Producto toProducto(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }
        return new Producto(
                productoDTO.getNombre(),
                productoDTO.getStock(),
                productoDTO.isPrecio()
        );
    }
}


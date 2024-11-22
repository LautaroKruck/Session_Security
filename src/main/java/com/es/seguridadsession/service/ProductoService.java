package com.es.seguridadsession.service;

import com.es.seguridadsession.dto.ProductoDTO;
import com.es.seguridadsession.exception.BadRequestException;
import com.es.seguridadsession.exception.NotFoundException;
import com.es.seguridadsession.model.Producto;
import com.es.seguridadsession.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene un producto buscándolo por su ID.
     *
     * @param id Identificador del producto.
     * @return ProductoDTO con los datos del producto.
     */
    public ProductoDTO getById(String id) {
        try {
            Long productId = Long.parseLong(id);
            Producto producto = productoRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + id));
            return new ProductoDTO(producto.getNombre(), producto.getStock(), producto.getPrecio());
        } catch (NumberFormatException e) {
            throw new BadRequestException("El ID del producto debe ser un número válido.");
        }
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param productoDTO Datos del producto a insertar.
     * @return ProductoDTO con los datos del producto recién creado.
     */
    public ProductoDTO insert(ProductoDTO productoDTO) {
        if (productoDTO.getNombre() == null || productoDTO.getNombre().isBlank()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío.");
        }

        if (productoDTO.getStock() < 0) {
            throw new BadRequestException("El stock del producto no puede ser negativo.");
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoDTO.getNombre());
        nuevoProducto.setStock(productoDTO.getStock());
        nuevoProducto.setPrecio(productoDTO.getPrecio());

        Producto productoGuardado = productoRepository.save(nuevoProducto);
        return new ProductoDTO(productoGuardado.getNombre(), productoGuardado.getStock(), productoGuardado.getPrecio());
    }
}

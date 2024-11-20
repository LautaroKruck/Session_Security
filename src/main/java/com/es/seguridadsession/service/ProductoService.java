package com.es.seguridadsession.service;

import com.es.seguridadsession.dto.ProductoDTO;
import com.es.seguridadsession.model.Producto;
import com.es.seguridadsession.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene un producto buscándolo por su ID
     * @param id
     * @return
     */
    public ProductoDTO getById(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    /**
     * Inserta un producto dentro la tabla productos
     * @param productoDTO
     * @return
     */
    public ProductoDTO insert(ProductoDTO productoDTO) {
        var producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(productoDTO.isPrecio());

        var savedProducto = productoRepository.save(producto);

        return new ProductoDTO(savedProducto.getNombre(), savedProducto.getStock(), savedProducto.isPrecio());
    }


}

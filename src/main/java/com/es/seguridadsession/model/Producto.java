package com.es.seguridadsession.model;


import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int stock;


    private boolean precio;

    public Producto(){}

    public Producto(Long id, String nombre, int stock, boolean precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto(String nombre, int stock, boolean precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isPrecio() {
        return precio;
    }

    public void setPrecio(boolean precio) {
        this.precio = precio;
    }
}

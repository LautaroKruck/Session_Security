package com.es.seguridadsession.dto;

public class UsuarioDTO {

    private String nombre;
    private String password;

    public UsuarioDTO(String nombre, String password1) {
        this.nombre = nombre;
        this.password = password1;
    }

    public UsuarioDTO(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

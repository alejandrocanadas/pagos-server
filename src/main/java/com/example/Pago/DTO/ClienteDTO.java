package com.example.Pago.DTO;


import java.util.Date;

import jakarta.persistence.Column;

public class ClienteDTO {
    private String correo;
    private String nombre;
    private String cedula;

    public ClienteDTO() {}

    public ClienteDTO(String correo, String nombre, String cedula) {
        this.correo = correo;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    // Getters y Setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula;}
}

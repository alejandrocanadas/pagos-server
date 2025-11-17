package com.example.Pago.DTO;

import lombok.Data;

@Data
public class ClienteRegistroDTO {
    private String nombre;
    private String cedula;
    private String correo;
    private String password;   
}
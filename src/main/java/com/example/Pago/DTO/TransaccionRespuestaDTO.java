package com.example.Pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaccionRespuestaDTO {
    private String correo;
    private double precio;
    private String estado;
}

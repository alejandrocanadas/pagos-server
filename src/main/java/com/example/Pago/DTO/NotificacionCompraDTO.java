package com.example.Pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionCompraDTO {

    private String correoCliente;
    private String estado;   // PAGADA / FALLIDA
    private Double monto;
}
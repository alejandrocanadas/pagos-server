package com.example.Pago.DTO;

import java.util.List;

import lombok.Data;


@Data
public class SolicitudPagoDTO {
    private Long compraId;
    private Long clienteId;
    private String correoCliente;
    private Double monto;

    private Long tarjetaId;        
    private String numeroTarjeta;  
    private Double saldoTarjeta;

    private List<ItemPagoDTO> items; 
}

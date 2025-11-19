package com.example.Pago.DTO;

import lombok.Data;

@Data
public class PagoProcesadoDTO {
    private Long compraId;
    private Long transaccionId;
    private String estado;      
    private Double monto;
    private String correoCliente;
}

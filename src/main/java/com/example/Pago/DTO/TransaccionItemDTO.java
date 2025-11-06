package com.example.Pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionItemDTO {
    private Long id;
    private Integer cantidad;
    private Double precioUnitario;
    private Long paqueteId;
}
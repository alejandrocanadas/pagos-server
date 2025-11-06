package com.example.Pago.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {
    private Long id;
    private Double total;
    private String estado;
    private LocalDateTime fecha;
    private String cedula;
    private Long tarjetaId;
    private List<TransaccionItemDTO> items;
}

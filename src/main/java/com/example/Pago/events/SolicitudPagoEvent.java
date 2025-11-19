package com.example.Pago.events;

import com.example.Pago.DTO.SolicitudPagoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudPagoEvent {
    private SolicitudPagoDTO solicitudPago;
}

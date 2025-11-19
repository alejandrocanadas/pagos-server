package com.example.Pago.events;

import com.example.Pago.DTO.PagoProcesadoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoProcesadoEvent {
    private PagoProcesadoDTO pagoProcesado;
}
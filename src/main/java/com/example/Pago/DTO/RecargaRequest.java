package com.example.Pago.DTO;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;


@Getter
@Setter
public class RecargaRequest {
    private String numero;
    private Double recarga;
}

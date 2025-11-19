package com.example.Pago.DTO;

import lombok.Data;

@Data
public class ItemPagoDTO {
    private Long paqueteId;
    private Double precio;
    private Integer cantidad;

    public ItemPagoDTO() {}

    public ItemPagoDTO(Long paqueteId, Double precio, Integer cantidad) {
        this.paqueteId = paqueteId;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Long getPaqueteId() { return paqueteId; }
    public void setPaqueteId(Long paqueteId) { this.paqueteId = paqueteId; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
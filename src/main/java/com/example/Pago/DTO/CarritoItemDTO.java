package com.example.Pago.DTO;

public class CarritoItemDTO {
    private Long id;
    private int cantidad;
    private Long productoId;
    private Long carritoId;

    public CarritoItemDTO() {}

    public CarritoItemDTO(Long id, int cantidad, Long productoId, Long carritoId) {
        this.id = id;
        this.cantidad = cantidad;
        this.productoId = productoId;
        this.carritoId = carritoId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public Long getCarritoId() { return carritoId; }
    public void setCarritoId(Long carritoId) { this.carritoId = carritoId; }
}

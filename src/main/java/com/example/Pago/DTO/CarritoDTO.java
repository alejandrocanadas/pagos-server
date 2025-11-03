package com.example.Pago.DTO;

public class CarritoDTO {
    private Long id;
    private Long clienteId;

    public CarritoDTO() {}

    public CarritoDTO(Long id, Long clienteId) {
        this.id = id;
        this.clienteId = clienteId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}


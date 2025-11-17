package com.example.Pago.DTO;

public class CarritoDTO {
    private Long id;
    private String cedulaCliente;

    public CarritoDTO() {}

    public CarritoDTO(Long id, String cedulaCliente) {
        this.id = id;
        this.cedulaCliente = cedulaCliente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}


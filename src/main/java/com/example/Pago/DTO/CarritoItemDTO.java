package com.example.Pago.DTO;

public class CarritoItemDTO {
    private Long id;
    private int cantidad;
    private String nombrePaquete; 
    private String cedulaCliente;

    public CarritoItemDTO() { }

    public CarritoItemDTO(Long id, int cantidad, String nombrePaquete, String cedulaCliente) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombrePaquete = nombrePaquete;
        this.cedulaCliente = cedulaCliente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getNombrePaquete() { return nombrePaquete; }
    public void setNombrePaquete(String nombrePaquete) { this.nombrePaquete = nombrePaquete; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}

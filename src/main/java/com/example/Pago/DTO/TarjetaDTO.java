package com.example.Pago.DTO;

import java.util.Date;

public class TarjetaDTO {
    private Long id;
    private String numeroTarjeta;
    private String tipo;
    private String fechaVencimiento;
    private String cedulaCliente;

    public TarjetaDTO() {}

    public TarjetaDTO(Long id, String numeroTarjeta, String tipo, String fechaVencimiento,  String cedulaCliente) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.tipo = tipo;
        this.fechaVencimiento = fechaVencimiento;

        this.cedulaCliente = cedulaCliente;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getnumeroTarjeta() { return numeroTarjeta; }
    public void setnumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}


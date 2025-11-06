package com.example.Pago.DTO;

import java.util.Date;

public class TarjetaDTO {
    private Long id;
    private String numero;
    private String tipo;
    private Date fechaVencimiento;
    private Double saldo;
    private String cedulaCliente;

    public TarjetaDTO() {}

    public TarjetaDTO(Long id, String numero, String tipo, Date fechaVencimiento, Double saldo,  String cedulaCliente) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.fechaVencimiento = fechaVencimiento;
        this.saldo = saldo;

        this.cedulaCliente = cedulaCliente;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}


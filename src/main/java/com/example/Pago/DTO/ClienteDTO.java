package com.example.Pago.DTO;


import java.util.Date;

public class ClienteDTO {
    private Long id;
    private String correo;
    private String nombre;
    private String tipo;              // Débito o Crédito
    private String tarjeta;
    private Date fechaVencimiento;
    private Double saldo;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String correo, String nombre, String tipo, String tarjeta, Date fechaVencimiento, Double saldo) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tarjeta = tarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.saldo = saldo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getTarjeta() { return tarjeta; }
    public void setTarjeta(String tarjeta) { this.tarjeta = tarjeta; }

    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
}

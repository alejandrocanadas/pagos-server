package com.example.Pago.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TransaccionDTO {
    private Long id;
    private Double monto;
    private String estado;
    private LocalDate fecha;
    private Long clienteId;

    public TransaccionDTO() {}

    public TransaccionDTO(Long id, Double monto, String estado, LocalDate fecha, Long clienteId) {
        this.id = id;
        this.monto = monto;
        this.estado = estado;
        this.fecha = fecha;
        this.clienteId = clienteId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}

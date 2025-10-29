package com.example.Pago.model;

import java.util.Date;

import com.example.Pago.repository.ClienteRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero; // número de tarjeta
    private Double monto;
    private Date fecha = new Date();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Método de negocio: Recargar tarjeta
    public static void recargarTarjeta(String numero, Double recarga, ClienteRepository repo) {
        Cliente c = repo.findByTarjeta(numero);
        if (c != null) {
            c.setSaldo(c.getSaldo() + recarga);
            repo.save(c);
        } else {
            throw new IllegalArgumentException("Tarjeta no encontrada");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}

package com.example.Pago.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private String estado;

    private Double total;

    private String cedula;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL)
    private List<TransaccionItem> items;
}

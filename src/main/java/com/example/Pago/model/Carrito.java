package com.example.Pago.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;
    private Integer cantidad;

    // Relación con carrito_items
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoItem> items = new ArrayList<>();
}

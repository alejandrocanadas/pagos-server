package com.example.Pago.model;

import java.util.Optional;

import com.example.Pago.repository.PaqueteRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paquete")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
    private String destino;

    
    public static Optional<Paquete> buscarPorId(Long id, PaqueteRepository repo) {
        return repo.findById(id);
    }

    public static void eliminarPorId(Long id, PaqueteRepository repo) {
        repo.deleteById(id);
    }
}

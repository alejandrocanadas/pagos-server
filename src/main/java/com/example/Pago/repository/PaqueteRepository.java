package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.Paquete;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    Paquete findByNombre(String nombre);
}

package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pago.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByTarjeta(String tarjeta);
}
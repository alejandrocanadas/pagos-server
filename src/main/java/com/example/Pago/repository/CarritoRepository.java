package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pago.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {}

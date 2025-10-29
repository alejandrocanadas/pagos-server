package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pago.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {}
package com.example.Pago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Tarjeta findByNumeroTarjeta(String numeroTarjeta);
    List<Tarjeta> findByClienteCedula(String cedula);
} 

package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pago.model.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {}

package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.CarritoItem;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {}

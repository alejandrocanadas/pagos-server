package com.example.Pago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.TransaccionItem;

@Repository
public interface TransaccionItemRepository extends JpaRepository<TransaccionItem, Long> {
    List<TransaccionItem> findByTransaccionId(Long transaccionId);
}
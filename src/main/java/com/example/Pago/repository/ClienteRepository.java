package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.Cliente;

import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCedula(String cedula);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente c WHERE c.cedula = :cedula")
    void deleteByCedula(String cedula);

    boolean existsByCedula(String cedula);
}
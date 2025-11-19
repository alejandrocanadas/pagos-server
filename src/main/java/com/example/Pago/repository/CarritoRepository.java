package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByCliente(Cliente cliente);

    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c.cliente.id = :id")
    Carrito findByClienteId(@Param("id") Long id);
}

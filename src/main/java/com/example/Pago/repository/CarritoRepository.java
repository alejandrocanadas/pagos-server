package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByCliente(Cliente cliente);
}

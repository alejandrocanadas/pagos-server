package com.example.Pago.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.TransaccionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    @Autowired
    private final TransaccionRepository transaccionRepository;
    @Autowired
    private final CarritoRepository carritoRepository;

    
    public List<Transaccion> listar() {
        return transaccionRepository.findAll();
    }

    public Transaccion anular(Long id) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
        if ("APROBADA".equals(t.getEstado())) {
            Cliente c = t.getCliente();
            c.setSaldo(c.getSaldo() + t.getMonto());
            t.setEstado("ANULADA");
            transaccionRepository.save(t);
        }
        return t;
    }
}
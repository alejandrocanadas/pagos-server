package com.example.Pago.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.TarjetaRepository;
import com.example.Pago.repository.TransaccionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    @Autowired
    private final TransaccionRepository transaccionRepository;
    @Autowired
    private final CarritoRepository carritoRepository;
    @Autowired
    private final TarjetaRepository tarjetaRepository;

    public List<Transaccion> listar() {
        return transaccionRepository.findAll();
    }

    @Transactional
    public Transaccion anular(Long id) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if ("APROBADA".equalsIgnoreCase(t.getEstado())) {
            Tarjeta tarjeta = t.getTarjeta();

            if (tarjeta == null) {
                throw new RuntimeException("No se encontró la tarjeta asociada a la transacción");
            }

            tarjeta.setSaldo(tarjeta.getSaldo() + t.getTotal());
            tarjetaRepository.save(tarjeta);


            t.setEstado("ANULADA");
            transaccionRepository.save(t);
        }

        return t;
    }

}
package com.example.Pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Paquete;
import com.example.Pago.repository.CarritoItemRepository;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.PaqueteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoItemService {

    @Autowired
    private final CarritoItemRepository carritoItemRepository;
    @Autowired
    private final CarritoRepository carritoRepository;
    @Autowired
    private final PaqueteRepository paqueteRepository;

    public CarritoItem agregarItem(Long carritoId, Long productoId, Integer cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Paquete paquete = paqueteRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));

        CarritoItem item = new CarritoItem();
        item.setCarrito(carrito);
        item.setProducto(paquete);
        item.setCantidad(cantidad);

        return carritoItemRepository.save(item);
    }

    public Optional<CarritoItem> obtenerPorCarrito(Long carritoId) {
        return carritoItemRepository.findById(carritoId);
    }

    public void eliminarItem(Long id) {
        carritoItemRepository.deleteById(id);
    }
}
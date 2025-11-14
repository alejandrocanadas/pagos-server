package com.example.Pago.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.DTO.TransaccionRespuestaDTO;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.model.Transaccion;
import com.example.Pago.model.TransaccionItem;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TarjetaRepository;
import com.example.Pago.repository.TransaccionItemRepository;
import com.example.Pago.repository.TransaccionRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TransaccionItemRepository transaccionItemRepository;

    public Carrito crearCarrito(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);
        return carritoRepository.save(carrito);
    }

    public Optional<Carrito> obtenerPorCliente(Long clienteId) {
        return carritoRepository.findById(clienteId);
    }

    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    @Transactional
    public Transaccion pagarCarrito(Long carritoId, String numeroTarjeta) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
        if (tarjeta == null)
            throw new RuntimeException("Tarjeta no encontrada");

        // Calcular total
        double total = carrito.getItems().stream()
                .mapToDouble(i -> i.getProducto().getPrecio() * i.getCantidad())
                .sum();

        boolean fondosSuficientes = tarjeta.getSaldo() >= total;

        // Crear transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setEstado(fondosSuficientes ? "APROBADA" : "RECHAZADA");
        transaccion.setTotal(total);
        transaccion.setCedula(tarjeta.getCliente().getCedula());
        transaccion.setTarjeta(tarjeta);

        transaccionRepository.save(transaccion);

        // Registrar items
        for (CarritoItem item : carrito.getItems()) {
            TransaccionItem ti = new TransaccionItem();
            ti.setCantidad(item.getCantidad());
            ti.setPrecioUnitario(item.getProducto().getPrecio());
            ti.setPaquete(item.getProducto());
            ti.setTransaccion(transaccion);
            transaccionItemRepository.save(ti);
        }

        // Si la transacción fue aprobada
        if (fondosSuficientes) {

            tarjeta.setSaldo(tarjeta.getSaldo() - total);
            tarjetaRepository.save(tarjeta);

            carrito.getItems().clear();
            carritoRepository.save(carrito);
        }

        return transaccion;
    }

}
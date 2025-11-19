package com.example.Pago.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TarjetaRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private CompraPublisherService compraPublisherService;


    public Carrito crearCarrito(String cedulaCliente) {
        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null)
            throw new RuntimeException("Cliente no encontrado");
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);
        return carritoRepository.save(carrito);
    }

    public Carrito obtenerPorCliente(String cedulaCliente) {
        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null)
            throw new RuntimeException("Cliente no encontrado");

        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null)
            throw new RuntimeException("Carrito no encontrado para el cliente");

        return carrito;
    }

    public void eliminarCarrito(String cedulaCliente) {

        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null)
            throw new RuntimeException("Cliente no encontrado");

        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null)
            throw new RuntimeException("El cliente no tiene carrito asignado");

        carritoRepository.delete(carrito);
    }

    
}

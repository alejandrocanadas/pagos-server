package com.example.Pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Paquete;
import com.example.Pago.repository.CarritoItemRepository;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.ClienteRepository;
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
    @Autowired
    private final ClienteRepository clienteRepository;

    public CarritoItem agregarItem(String cedulaCliente, String nombrePaquete, Integer cantidad) {
        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con cédula: " + cedulaCliente);
        }

        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null) {
            throw new RuntimeException("El cliente no tiene carrito.");
        }

        Paquete paquete = paqueteRepository.findByNombre(nombrePaquete);
        if (paquete == null) {
            throw new RuntimeException("Paquete no encontrado: " + nombrePaquete);
        }

        CarritoItem item = new CarritoItem();
        item.setCarrito(carrito);
        item.setProducto(paquete);
        item.setCantidad(cantidad);

        return carritoItemRepository.save(item);
    }


    public List<CarritoItem> obtenerPorCarrito(String cedulaCliente) {

        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con cédula: " + cedulaCliente);
        }

        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null) {
            throw new RuntimeException("El cliente no tiene carrito.");
        }

        return carrito.getItems();
    }

    public void eliminarItem(Long id) {
        carritoItemRepository.deleteById(id);
    }
}
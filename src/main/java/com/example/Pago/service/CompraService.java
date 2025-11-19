package com.example.Pago.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.DTO.ItemPagoDTO;
import com.example.Pago.DTO.SolicitudPagoDTO;
import com.example.Pago.events.SolicitudPagoEvent;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Compra;
import com.example.Pago.model.Compra;
import com.example.Pago.model.EstadoCompra;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.CompraRepository;
import com.example.Pago.repository.TarjetaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private TarjetaRepository tarjetaRepo;

    @Autowired
    private CarritoRepository carritoRepo;

    @Autowired
    private CompraRepository compraRepo;

    @Autowired
    private CompraPublisherService publisher;

    @Transactional
    public Compra crearCompra(String cedulaCliente, String numeroTarjeta) {

        Cliente cliente = clienteRepo.findByCedula(cedulaCliente);
        if (cliente == null)
            throw new RuntimeException("Cliente no existe");

        Tarjeta tarjeta = tarjetaRepo.findByNumeroTarjeta(numeroTarjeta);
        if (tarjeta == null)
            throw new RuntimeException("Tarjeta no encontrada");

        Carrito carrito = carritoRepo.findByCliente(cliente);
        if (carrito == null)
            throw new RuntimeException("No hay carrito");

        double total = carrito.getItems().stream()
                .mapToDouble(i -> i.getProducto().getPrecio() * i.getCantidad())
                .sum();

        if (tarjeta.getSaldo() < total)
            throw new RuntimeException("Fondos insuficientes");

        // Crear Compra
        Compra compra = new Compra();
        compra.setClienteId(cliente.getId());
        compra.setTarjetaId(tarjeta.getId());
        compra.setMonto(total);
        compra.setCorreoCliente(cliente.getCorreo());
        compra.setFechaCreacion(LocalDateTime.now());
        compra.setEstado(EstadoCompra.PENDIENTE);
        compraRepo.save(compra);

        // Crear DTO para PAGOS
        SolicitudPagoDTO dto = new SolicitudPagoDTO();
        dto.setCompraId(compra.getId());
        dto.setClienteId(cliente.getId());
        dto.setCorreoCliente(cliente.getCorreo());
        dto.setMonto(total);
        dto.setTarjetaId(tarjeta.getId()); // Pagos necesita el ID para registro local
        dto.setNumeroTarjeta(numeroTarjeta); // Para validación real si lo requieres
        dto.setSaldoTarjeta(tarjeta.getSaldo());

        List<ItemPagoDTO> items = carrito.getItems().stream().map(ci -> {
            ItemPagoDTO ip = new ItemPagoDTO();
            ip.setPaqueteId(ci.getProducto().getId());
            ip.setPrecio(ci.getProducto().getPrecio());
            ip.setCantidad(ci.getCantidad());
            return ip;
        }).toList();

        dto.setItems(items);

        // Enviar solicitud a Pagos
        publisher.enviarSolicitudPago(new SolicitudPagoEvent(dto));

        return compra;
    }

}

package com.example.Pago.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    @Autowired
    private PagoPublisherService pagosPublisher;


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

    @Transactional
    public TransaccionRespuestaDTO pagarCarrito(String cedulaCliente, String numeroTarjeta) {

        Cliente cliente = clienteRepository.findByCedula(cedulaCliente);
        if (cliente == null)
            throw new RuntimeException("Cliente no encontrado");

        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null)
            throw new RuntimeException("El cliente no tiene carrito activo");

        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
        if (tarjeta == null)
            throw new RuntimeException("Tarjeta no encontrada");

        double total = carrito.getItems().stream()
                .mapToDouble(i -> i.getProducto().getPrecio() * i.getCantidad())
                .sum();

        boolean fondosSuficientes = tarjeta.getSaldo() >= total;

        // Crear transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setEstado(fondosSuficientes ? "APROBADA" : "RECHAZADA");
        transaccion.setTotal(total);
        transaccion.setCedula(cliente.getCedula());
        transaccion.setTarjeta(tarjeta);
        transaccionRepository.save(transaccion);

        // Guardar items
        for (CarritoItem item : carrito.getItems()) {
            TransaccionItem ti = new TransaccionItem();
            ti.setCantidad(item.getCantidad());
            ti.setPrecioUnitario(item.getProducto().getPrecio());
            ti.setPaquete(item.getProducto());
            ti.setTransaccion(transaccion);
            transaccionItemRepository.save(ti);
        }

        // Si se aprobó, descontar saldo y limpiar carrito
        if (fondosSuficientes) {
            tarjeta.setSaldo(tarjeta.getSaldo() - total);
            tarjetaRepository.save(tarjeta);

            carrito.getItems().clear();
            carritoRepository.save(carrito);
        }

        // Enviar mensaje por cola
        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put("correo", cliente.getCorreo());
        mensaje.put("nombre", cliente.getNombre());
        mensaje.put("estado", transaccion.getEstado());
        mensaje.put("total", total);

        pagosPublisher.enviarResultadoPago(mensaje);

        return new TransaccionRespuestaDTO(
                cliente.getCorreo(),
                total,
                transaccion.getEstado());
    }
}

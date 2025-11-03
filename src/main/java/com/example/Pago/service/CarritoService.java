package com.example.Pago.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.DTO.TransaccionRespuestaDTO;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TransaccionRepository;

import jakarta.transaction.Transactional;

@Service
public
class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

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
    public TransaccionRespuestaDTO pagarCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Cliente cliente = carrito.getCliente();
        double total = carrito.getItems().stream()
                .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
                .sum();

        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(cliente);
        transaccion.setMonto(total);
        transaccion.setFecha(LocalDate.now());

        String estado;
        if (cliente.getSaldo() >= total) {
            cliente.setSaldo(cliente.getSaldo() - total);
            clienteRepository.save(cliente);
            estado = "APROBADA";
        } else {
            estado = "RECHAZADA";
        }

        transaccion.setEstado(estado);
        transaccionRepository.save(transaccion);

        return new TransaccionRespuestaDTO(
                cliente.getCorreo(),
                cliente.getNombre(),
                estado
        );
    }
    
}
package com.example.Pago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TransaccionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con id " + id + " no encontrado"));
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente datos) {
        Cliente cliente = obtenerPorId(id);
        cliente.setNombre(datos.getNombre());
        cliente.setCorreo(datos.getCorreo());
        cliente.setSaldo(datos.getSaldo());
        cliente.setTipo(datos.getTipo());
        cliente.setTarjeta(datos.getTarjeta());
        cliente.setFechaVencimiento(datos.getFechaVencimiento());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente con id " + id + " no existe");
        }
        clienteRepository.deleteById(id);
    }

    public Cliente recargarSaldo(String numeroTarjeta, Double monto) {
        Cliente cliente = clienteRepository.findByTarjeta(numeroTarjeta);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con la tarjeta: " + numeroTarjeta);
        }
        if (monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        cliente.recargar(monto);
        return clienteRepository.save(cliente);
    }
}

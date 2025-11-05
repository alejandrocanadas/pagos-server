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

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID " + id));
    }

    public Cliente actualizarCliente(String cedula, Cliente datos) {
        Cliente cliente = obtenerPorCedula(cedula);
        cliente.setNombre(datos.getNombre());
        cliente.setCorreo(datos.getCorreo());
        cliente.setSaldo(datos.getSaldo());
        cliente.setTipo(datos.getTipo());
        cliente.setTarjeta(datos.getTarjeta());
        cliente.setFechaVencimiento(datos.getFechaVencimiento());
        return clienteRepository.save(cliente);
    }

    public void eliminarPorCedula(String cedula) {
        if (!clienteRepository.existsByCedula(cedula))
            throw new RuntimeException("Cliente no encontrado con cédula " + cedula);
        clienteRepository.deleteByCedula(cedula);
    }


    //CAMBIO
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

    public Cliente obtenerPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    public boolean existePorCedula(String cedula) {
        return clienteRepository.existsByCedula(cedula);
    }
}

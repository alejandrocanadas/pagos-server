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
        return clienteRepository.save(cliente);
    }

    public void eliminarPorCedula(String cedula) {
        if (!clienteRepository.existsByCedula(cedula))
            throw new RuntimeException("Cliente no encontrado con cédula " + cedula);
        clienteRepository.deleteByCedula(cedula);
    }

    public Cliente obtenerPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    public boolean existePorCedula(String cedula) {
        return clienteRepository.existsByCedula(cedula);
    }
}

package com.example.Pago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Pago.DTO.ClienteRegistroDTO;
import com.example.Pago.DTO.RegistroRequest;
import com.example.Pago.model.Cliente;
import com.example.Pago.repository.ClienteRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente crearCliente(ClienteRegistroDTO dto) {

        if (clienteRepository.existsByCedula(dto.getCedula())) {
            throw new RuntimeException("Ya existe un cliente con esta cédula");
        }
        
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCedula(dto.getCedula());
        cliente.setCorreo(dto.getCorreo());

        Cliente guardado = clienteRepository.save(cliente);

        
        RegistroRequest req = new RegistroRequest();
        req.setEmail(dto.getCorreo());
        req.setPassword(dto.getPassword());
        req.setRole("CLIENT");

        try {
            restTemplate.postForObject("http://localhost:8081/auth/register", req, Void.class);
        } catch (Exception e) {
            clienteRepository.delete(guardado);
            throw new RuntimeException("No se pudo registrar el usuario en Auth-Service");
        }

        return guardado;
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

package com.example.Pago.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pago.model.Cliente;
import com.example.Pago.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente datos) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(datos.getNombre());
                    cliente.setCorreo(datos.getCorreo());
                    cliente.setSaldo(datos.getSaldo());
                    cliente.setTipo(datos.getTipo());
                    cliente.setTarjeta(datos.getTarjeta());
                    cliente.setFechaVencimiento(datos.getFechaVencimiento());
                    return ResponseEntity.ok(clienteRepository.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) return ResponseEntity.notFound().build();
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


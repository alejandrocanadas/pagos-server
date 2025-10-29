package com.example.Pago.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pago.model.Paquete;
import com.example.Pago.repository.PaqueteRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/paquetes")
@RequiredArgsConstructor
public class PaqueteController {

    private final PaqueteRepository paqueteRepository;

    @GetMapping
    public List<Paquete> listarPaquetes() {
        return paqueteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPorId(@PathVariable Long id) {
        return paqueteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paquete> crearPaquete(@RequestBody Paquete paquete) {
        Paquete nuevo = paqueteRepository.save(paquete);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Long id) {
        if (!paqueteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paqueteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

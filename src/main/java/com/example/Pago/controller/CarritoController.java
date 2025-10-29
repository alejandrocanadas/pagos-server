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

import com.example.Pago.model.Carrito;
import com.example.Pago.repository.CarritoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoRepository carritoRepository;

    @GetMapping
    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerPorId(@PathVariable Long id) {
        return carritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        Carrito nuevo = carritoRepository.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        if (!carritoRepository.existsById(id)) return ResponseEntity.notFound().build();
        carritoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

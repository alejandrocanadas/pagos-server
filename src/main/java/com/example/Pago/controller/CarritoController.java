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

import com.example.Pago.DTO.TransaccionRespuestaDTO;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito dto) {
        Carrito nuevo = carritoService.crearCarrito(dto.getCliente().getId());
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Carrito> obtenerPorCliente(@PathVariable Long clienteId) {
        return carritoService.obtenerPorCliente(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<TransaccionRespuestaDTO> pagarCarrito(@PathVariable Long id) {
        TransaccionRespuestaDTO transaccion = carritoService.pagarCarrito(id);
        return ResponseEntity.ok(transaccion);
    }
}
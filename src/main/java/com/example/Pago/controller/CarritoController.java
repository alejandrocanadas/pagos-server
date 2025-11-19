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
import com.example.Pago.DTO.CarritoDTO;
import com.example.Pago.DTO.PagoRequest;
import com.example.Pago.model.Carrito;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.service.CarritoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody CarritoDTO dto) {
        Carrito nuevo = carritoService.crearCarrito(dto.getCedulaCliente());
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{cedulaCliente}")
    public ResponseEntity<Carrito> obtenerPorCliente(@PathVariable String cedulaCliente) {
        try {
            Carrito carrito = carritoService.obtenerPorCliente(cedulaCliente);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cedulaCliente}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable String cedulaCliente) {
        carritoService.eliminarCarrito(cedulaCliente);
        return ResponseEntity.noContent().build();
    }
}
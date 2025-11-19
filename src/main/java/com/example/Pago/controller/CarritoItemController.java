package com.example.Pago.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Pago.DTO.CarritoItemDTO;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.service.CarritoItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito-items")
@RequiredArgsConstructor
public class CarritoItemController {

    private final CarritoItemService carritoItemService;

    @PostMapping
    public ResponseEntity<CarritoItem> agregarItem(@RequestBody CarritoItemDTO dto) {
        CarritoItem nuevo = carritoItemService.agregarItem(
                dto.getCedulaCliente(),
                dto.getNombrePaquete(),
                dto.getCantidad());
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{cedulaCliente}")
    public List<CarritoItem> obtenerPorCarrito(@PathVariable String cedulaCliente) {
        return carritoItemService.obtenerPorCarrito(cedulaCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        carritoItemService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }
}
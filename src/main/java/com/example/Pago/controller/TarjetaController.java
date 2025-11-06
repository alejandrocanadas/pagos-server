package com.example.Pago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Pago.DTO.RecargaRequest;
import com.example.Pago.DTO.TarjetaDTO;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.service.TarjetaService;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody TarjetaDTO dto) {
        try {
            Tarjeta nuevaTarjeta = tarjetaService.crearTarjeta(dto);
            return ResponseEntity.ok(nuevaTarjeta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<List<Tarjeta>> listarPorCliente(
            @PathVariable String cedula) {
        List<Tarjeta> tarjetas = tarjetaService.listarPorCliente(cedula);
        return ResponseEntity.ok(tarjetas);
    }

    @PostMapping("/recargar")
    public ResponseEntity<Tarjeta> recargarTarjeta(@RequestBody RecargaRequest request) {
        try {
            Tarjeta tarjeta = tarjetaService.recargar(request.getNumeroTarjeta(), request.getMonto());
            return ResponseEntity.ok(tarjeta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarjeta(@PathVariable Long id) {
        try {
            tarjetaService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.example.Pago.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TransaccionRepository;
import com.example.Pago.service.TransaccionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> listar() {
        return transaccionService.listar();
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<String> anular(@PathVariable Long id) {
        Transaccion t = transaccionService.anular(id);
        return ResponseEntity.ok("Transacción " + t.getId() + " anulada con éxito.");
    }
}

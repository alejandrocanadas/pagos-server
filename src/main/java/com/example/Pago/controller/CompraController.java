package com.example.Pago.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pago.model.Compra;
import com.example.Pago.service.CompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping("/crear")
    public ResponseEntity<Compra> crearCompra(
            @RequestParam String cedula,
            @RequestParam String numeroTarjeta) {

        Compra compra = compraService.crearCompra(cedula, numeroTarjeta);
        return ResponseEntity.ok(compra);
    }
}

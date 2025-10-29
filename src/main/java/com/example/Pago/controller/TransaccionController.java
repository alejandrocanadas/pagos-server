package com.example.Pago.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pago.DTO.RecargaRequest;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TransaccionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionRepository transaccionRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion) {
        Transaccion nueva = transaccionRepository.save(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }


    @PostMapping("/recargar")
    public ResponseEntity<String> recargar(@RequestBody RecargaRequest request) {
    String numero = request.getNumero();
    Double monto = request.getRecarga();

    Cliente cliente = clienteRepository.findByTarjeta(numero);
    if (cliente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarjeta no encontrada");
    }

    cliente.setSaldo(cliente.getSaldo() + monto);
    clienteRepository.save(cliente);

    Transaccion transaccion = new Transaccion();
    transaccion.setNumero(numero);
    transaccion.setMonto(monto);
    transaccion.setCliente(cliente);
    transaccionRepository.save(transaccion);

    return ResponseEntity.ok("Recarga exitosa. Nuevo saldo: " + cliente.getSaldo());
}

}

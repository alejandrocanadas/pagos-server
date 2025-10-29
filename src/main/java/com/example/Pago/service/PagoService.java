package com.example.Pago.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.model.Cliente;
import com.example.Pago.model.Transaccion;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TransaccionRepository;

@Service
public class PagoService {
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private TransaccionRepository transRepo;

    public String recargarTarjeta(String numero, Double monto) {
        Cliente cliente = clienteRepo.findByTarjeta(numero);
        if (cliente == null) return "Tarjeta no encontrada";

        cliente.setSaldo(cliente.getSaldo() + monto);
        clienteRepo.save(cliente);

        Transaccion t = new Transaccion();
        t.setNumero(numero);
        t.setMonto(monto);
        t.setCliente(cliente);
        transRepo.save(t);

        return "Recarga exitosa. Nuevo saldo: " + cliente.getSaldo();
    }
}

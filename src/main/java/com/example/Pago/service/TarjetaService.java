package com.example.Pago.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pago.DTO.TarjetaDTO;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.repository.ClienteRepository;
import com.example.Pago.repository.TarjetaRepository;
import java.util.Optional;

@Service
public class TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Tarjeta crearTarjeta(TarjetaDTO dto) {
    Cliente cliente = clienteRepository.findByCedula(dto.getCedulaCliente());
    if (cliente == null) {
        throw new RuntimeException("Cliente no encontrado con cédula " + dto.getCedulaCliente());
    }

    Tarjeta tarjeta = new Tarjeta();
    tarjeta.setNumeroTarjeta(dto.getnumeroTarjeta());
    tarjeta.setTipo(dto.getTipo());
    tarjeta.setSaldo(0.0);
    tarjeta.setFechaVencimiento(LocalDate.parse(dto.getFechaVencimiento()));
    tarjeta.setCliente(cliente);

    return tarjetaRepository.save(tarjeta);
}


    public List<Tarjeta> listarPorCliente(String cedula) {
        return tarjetaRepository.findByClienteCedula(cedula);
    }

    public Tarjeta recargar(String numero, Double monto) {
        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numero);
        if (tarjeta == null) {
            throw new RuntimeException("Tarjeta no encontrada");
        }
        tarjeta.setSaldo(tarjeta.getSaldo() + monto);
        return tarjetaRepository.save(tarjeta);
    }

    public void eliminar(Long id) {
        tarjetaRepository.deleteById(id);
    }
}

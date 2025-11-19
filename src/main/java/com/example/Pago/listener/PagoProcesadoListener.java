package com.example.Pago.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.Pago.DTO.PagoProcesadoDTO;
import com.example.Pago.events.PagoProcesadoEvent;
import com.example.Pago.model.Carrito;
import com.example.Pago.model.Compra;
import com.example.Pago.model.EstadoCompra;
import com.example.Pago.model.Tarjeta;
import com.example.Pago.repository.CarritoRepository;
import com.example.Pago.repository.CompraRepository;
import com.example.Pago.repository.TarjetaRepository;
import com.example.Pago.service.NotificacionPublisherService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagoProcesadoListener {

    private final CompraRepository compraRepo;
    private final TarjetaRepository tarjetaRepo;
    private final CarritoRepository carritoRepo;
    private final NotificacionPublisherService notificacionPublisher;

    @Transactional  // <-- ESTA LÍNEA SOLUCIONA TU PROBLEMA
    @RabbitListener(queues = "pago.procesado.queue")
    public void recibirPagoProcesado(PagoProcesadoEvent event) {

        PagoProcesadoDTO dto = event.getPagoProcesado();

        Compra compra = compraRepo.findById(dto.getCompraId())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        compra.setEstado(
                dto.getEstado().equals("APROBADA")
                        ? EstadoCompra.APROBADA
                        : EstadoCompra.RECHAZADA
        );
        compraRepo.save(compra);

        if (compra.getEstado() == EstadoCompra.APROBADA) {

            Tarjeta tarjeta = tarjetaRepo.findById(compra.getTarjetaId())
                    .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

            tarjeta.setSaldo(tarjeta.getSaldo() - dto.getMonto());
            tarjetaRepo.save(tarjeta);

            Carrito carrito = carritoRepo.findByClienteId(compra.getClienteId());
            carrito.getItems().clear();        
            carritoRepo.save(carrito);
        }

        notificacionPublisher.enviarNotificacionCompra(compra);
    }
}
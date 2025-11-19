package com.example.Pago.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionPublisherService {
    private final RabbitTemplate rabbitTemplate;

    public void enviarNotificacionCompra(Object notificacionDTO) {
        rabbitTemplate.convertAndSend(
                "notificaciones.exchange",
                "notificacion.compra",
                notificacionDTO
        );
    }
}

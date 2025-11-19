package com.example.Pago.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.Pago.config.RabbitConfig;
import com.example.Pago.events.SolicitudPagoEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraPublisherService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarSolicitudPago(SolicitudPagoEvent event) {
        rabbitTemplate.convertAndSend(
                "compras.exchange",
                "compra.solicitud.pago",
                event
        );
    }
}

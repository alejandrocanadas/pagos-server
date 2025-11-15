package com.example.Pago.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.Pago.config.RabbitConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoPublisherService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public void enviarResultadoPago(Object mensaje) {
        try {
            String json = objectMapper.writeValueAsString(mensaje);

            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ROUTING_KEY,
                    json);

            System.out.println("Mensaje enviado a RabbitMQ: " + json);

        } catch (Exception e) {
            throw new RuntimeException("Error enviando mensaje a RabbitMQ", e);
        }
    }
}

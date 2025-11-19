package com.example.Pago.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    
    // Exchange donde COMPRAS publica solicitudes de pago
    @Bean
    public DirectExchange comprasExchange() {
        return new DirectExchange("compras.exchange");
    }

    // Exchange donde PAGOS publica respuestas
    @Bean
    public DirectExchange pagosExchange() {
        return new DirectExchange("pagos.exchange");
    }

    @Bean
    public Queue pagoProcesadoQueue() {
        return new Queue("pago.procesado.queue", true);
    }

    // Cola que PAGOS escucha (solicitud de compra)
    @Bean
    public Queue solicitudPagoQueue() {
        return new Queue("compras.solicitud.pago.queue", true);
    }

    @Bean
    public Binding bindingPagoProcesado() {
        return BindingBuilder
                .bind(pagoProcesadoQueue())
                .to(pagosExchange())
                .with("pago.procesado");
    }

    @Bean
    public Binding bindingSolicitudPago() {
        return BindingBuilder
                .bind(solicitudPagoQueue())
                .to(comprasExchange())
                .with("compra.solicitud.pago");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
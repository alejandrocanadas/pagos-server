package com.example.Pago.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "pagos.exchange";
    public static final String QUEUE_PAGOS = "pagos.queue";
    public static final String ROUTING_KEY = "pagos.resultado";

    @Bean
    public DirectExchange pagosExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue pagosQueue() {
        return new Queue(QUEUE_PAGOS);
    }

    @Bean
    public Binding pagosBinding(Queue pagosQueue, DirectExchange pagosExchange) {
        return BindingBuilder.bind(pagosQueue).to(pagosExchange).with(ROUTING_KEY);
    }
}

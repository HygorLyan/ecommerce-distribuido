package com.exemplo.checkout;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PEDIDO_CRIADO_EXCHANGE = "pedido.criado.exchange";

    @Bean
    public FanoutExchange pedidoCriadoExchange() {
        return new FanoutExchange(PEDIDO_CRIADO_EXCHANGE);
    }
}
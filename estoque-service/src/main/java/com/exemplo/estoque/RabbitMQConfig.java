package com.exemplo.estoque;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PEDIDO_CRIADO_EXCHANGE = "pedido.criado.exchange";
    public static final String PEDIDO_CRIADO_QUEUE = "pedido.criado.estoque.queue";

    @Bean
    public FanoutExchange pedidoCriadoExchange() {
        return new FanoutExchange(PEDIDO_CRIADO_EXCHANGE);
    }

    @Bean
    public Queue pedidoCriadoQueue() {
        return new Queue(PEDIDO_CRIADO_QUEUE, true);
    }

    @Bean
    public Binding bindingPedidoCriado() {
        return BindingBuilder
                .bind(pedidoCriadoQueue())
                .to(pedidoCriadoExchange());
    }
}
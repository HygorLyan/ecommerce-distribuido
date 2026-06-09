package com.exemplo.estoque;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PedidoCriadoConsumer {

    @RabbitListener(queues = RabbitMQConfig.PEDIDO_CRIADO_QUEUE)
    public void consumirPedidoCriado(Message message) {
        String mensagem = new String(message.getBody(), StandardCharsets.UTF_8);

        System.out.println("=================================");
        System.out.println("EVENTO RECEBIDO NO ESTOQUE");
        System.out.println(mensagem);
        System.out.println("=================================");
    }
}

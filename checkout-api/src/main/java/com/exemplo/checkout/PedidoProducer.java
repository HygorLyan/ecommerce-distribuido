package com.exemplo.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public PedidoProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publicarPedidoCriado(PedidoCriadoEvent event) {
        try {
            String mensagem = objectMapper.writeValueAsString(event);

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.PEDIDO_CRIADO_EXCHANGE,
                    "",
                    mensagem
            );

            System.out.println("Evento PedidoCriado publicado no RabbitMQ: " + event.getPedidoId());

        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter evento PedidoCriado para JSON: " + event.getPedidoId());
        } catch (Exception e) {
            System.out.println("RabbitMQ indisponivel. Simulando publicacao do evento PedidoCriado: " + event.getPedidoId());
        }
    }
}

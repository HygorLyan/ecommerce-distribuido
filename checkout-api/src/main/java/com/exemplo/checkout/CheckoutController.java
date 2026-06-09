package com.exemplo.checkout;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class CheckoutController {

    private final EstoqueClient estoqueClient;
    private final PedidoProducer pedidoProducer;

    public CheckoutController(EstoqueClient estoqueClient, PedidoProducer pedidoProducer) {
        this.estoqueClient = estoqueClient;
        this.pedidoProducer = pedidoProducer;
    }

    @GetMapping("/estoque")
    public ResponseEntity<?> verificarEstoque(
            @RequestParam String produto,
            @RequestParam(defaultValue = "1") int quantidade) {

        if (produto == null || produto.isBlank()) {
            return ResponseEntity.badRequest().body("Produto obrigatorio");
        }

        if (quantidade <= 0) {
            return ResponseEntity.badRequest().body("Quantidade deve ser maior que zero");
        }

        try {
            EstoqueResposta resposta = estoqueClient.verificarEstoque(produto, quantidade);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao consultar o servico de estoque");
        }
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody PedidoRequest pedido) {

        if (pedido.getProduto() == null || pedido.getProduto().isBlank()) {
            return ResponseEntity.badRequest().body("Produto obrigatorio");
        }

        if (pedido.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().body("Quantidade deve ser maior que zero");
        }

        try {
            EstoqueResposta resposta = estoqueClient.verificarEstoque(
                    pedido.getProduto(),
                    pedido.getQuantidade()
            );

            if (resposta != null && resposta.isDisponivel()) {

                PedidoCriadoEvent evento = new PedidoCriadoEvent(
                        pedido.getProduto(),
                        pedido.getQuantidade()
                );

                pedidoProducer.publicarPedidoCriado(evento);

                System.out.println("Pedido criado: " + pedido.getProduto()
                        + " - Quantidade: " + pedido.getQuantidade());

                return ResponseEntity.ok("Pedido criado com sucesso. Evento PedidoCriado publicado.");
            }

            return ResponseEntity.badRequest().body("Produto sem estoque suficiente");

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao comunicar com o servico de estoque ou RabbitMQ");
        }
    }
}

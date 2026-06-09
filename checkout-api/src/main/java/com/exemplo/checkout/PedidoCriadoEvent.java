package com.exemplo.checkout;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoCriadoEvent implements Serializable {

    private String pedidoId;
    private String produto;
    private int quantidade;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;

    public PedidoCriadoEvent() {
    }

    public PedidoCriadoEvent(String produto, int quantidade) {
        this.pedidoId = UUID.randomUUID().toString();
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = BigDecimal.valueOf(quantidade * 50.0);
        this.dataCriacao = LocalDateTime.now();
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
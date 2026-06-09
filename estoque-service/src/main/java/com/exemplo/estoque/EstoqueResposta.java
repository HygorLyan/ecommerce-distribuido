package com.exemplo.estoque;

public class EstoqueResposta {

    private String produto;
    private int quantidadeSolicitada;
    private int quantidadeDisponivel;
    private boolean disponivel;
    private String mensagem;

    public EstoqueResposta() {
    }

    public EstoqueResposta(String produto, int quantidadeSolicitada, int quantidadeDisponivel, boolean disponivel, String mensagem) {
        this.produto = produto;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.disponivel = disponivel;
        this.mensagem = mensagem;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getMensagem() {
        return mensagem;
    }
}
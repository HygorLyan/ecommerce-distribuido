package com.exemplo.checkout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstoqueClient {

    private final RestTemplate restTemplate;
    private final String estoqueServiceUrl;

    public EstoqueClient(
            RestTemplate restTemplate,
            @Value("${estoque.service.url:http://localhost:8090}") String estoqueServiceUrl) {
        this.restTemplate = restTemplate;
        this.estoqueServiceUrl = estoqueServiceUrl;
    }

    public EstoqueResposta verificarEstoque(String produto, int quantidade) {
        String url = estoqueServiceUrl + "/estoque/verificar?produto=" + produto + "&quantidade=" + quantidade;
        ResponseEntity<EstoqueResposta> response =
                restTemplate.getForEntity(url, EstoqueResposta.class);

        return response.getBody();
    }
}

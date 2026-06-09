package com.exemplo.estoque;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private Map<String, Integer> estoque = new HashMap<>();

    public EstoqueController() {
        estoque.put("produto_A", 10);
        estoque.put("produto_B", 5);
        estoque.put("produto_C", 18);
        estoque.put("produto_D", 3);
    }

    @GetMapping("/verificar")
public ResponseEntity<?> verificarEstoque(
        @RequestParam String produto,
        @RequestParam int quantidade) {

    if (quantidade <= 0) {
        return ResponseEntity.badRequest().body("Quantidade invalida");
    }

    if (!estoque.containsKey(produto)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Produto nao encontrado");
    }

    int disponivel = estoque.get(produto);

    if (disponivel >= quantidade) {
        return ResponseEntity.ok(
                new EstoqueResposta(
                        produto,
                        quantidade,
                        disponivel,
                        true,
                        "Produto disponivel"
                )
            );
        } else {
            return ResponseEntity.ok(
                    new EstoqueResposta(
                            produto,
                            quantidade,
                            disponivel,
                            false,
                            "Estoque insuficiente"
                    )
            );
        }
    }
}

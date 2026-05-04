package com.lucefull.finance.controller;


import com.lucefull.finance.dto.CompraRequest;
import com.lucefull.finance.entity.Transacao;
import com.lucefull.finance.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final TransacaoService transacaoService;

    @PostMapping("/compra")
    public ResponseEntity<Transacao> realizarCompra(@RequestBody CompraRequest request) {
        Transacao transacao = transacaoService.realizarCompra(request);
        return ResponseEntity.ok(transacao);
    }
}

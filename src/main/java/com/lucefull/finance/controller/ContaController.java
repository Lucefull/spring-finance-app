package com.lucefull.finance.controller;

import com.lucefull.finance.entity.Conta;
import com.lucefull.finance.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaRepository contaRepository;

    @GetMapping
    public ResponseEntity<List<Conta>> listarTodas() {
        return ResponseEntity.ok(contaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaRepository.save(conta));
    }
}


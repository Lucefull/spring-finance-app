package com.lucefull.finance.service.impl;

import com.lucefull.finance.entity.Conta;
import com.lucefull.finance.repository.ContaRepository;
import com.lucefull.finance.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {
    private final ContaRepository contaRepository;

    public Conta criarNovaConta(Conta conta){
        return contaRepository.save(conta);
    }

    public List<Conta> consultarContasUsuario(String usuarioId){
        return contaRepository.findAllByUsuarioId(java.util.UUID.fromString(usuarioId));

    }

}

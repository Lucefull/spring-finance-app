package com.lucefull.finance.service;

import com.lucefull.finance.dto.CompraRequest;
import com.lucefull.finance.entity.Transacao;

public interface TransacaoService {
    Transacao realizarCompra(CompraRequest request);
}

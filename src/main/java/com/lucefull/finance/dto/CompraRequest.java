package com.lucefull.finance.dto;

import java.math.BigDecimal;

public record CompraRequest(String descricao,
                            BigDecimal valorTotal,
                            Long categoriaId,
                            Long contaId,
                            Long cartaoId,
                            boolean eCredito,
                            Integer parcelas) {
}

package com.lucefull.finance.service.impl;

import com.lucefull.finance.dto.CompraRequest;
import com.lucefull.finance.entity.CartaoCredito;
import com.lucefull.finance.entity.Conta;
import com.lucefull.finance.entity.Fatura;
import com.lucefull.finance.entity.Transacao;
import com.lucefull.finance.repository.CartaoCreditoRepository;
import com.lucefull.finance.repository.ContaRepository;
import com.lucefull.finance.repository.FaturaRepository;
import com.lucefull.finance.repository.TransacaoRepository;
import com.lucefull.finance.service.TransacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {
        
        private final TransacaoRepository transacaoRepository;
        
        private final ContaRepository contaRepository;
        
        private final CartaoCreditoRepository cartaoRepository;
        
        private final FaturaRepository faturaRepository;

        public Transacao realizarCompra(CompraRequest request) {
            Conta conta = contaRepository.findById(request.contaId())
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

            Transacao transacao = new Transacao();
            transacao.setDescricao(request.descricao());
            transacao.setValor(request.valorTotal());
            transacao.setData(LocalDateTime.now());
            transacao.setTipo("DESPESA");
            transacao.setConta(conta);

            if (request.eCredito()) {
                processarCompraCredito(request, conta);
            } else {
                processarCompraDebito(transacao, conta);
            }

            return transacaoRepository.save(transacao);
        }

        private void processarCompraDebito(Transacao transacao, Conta conta) {
            if (conta.getSaldo().compareTo(transacao.getValor()) < 0) {
                throw new RuntimeException("Saldo insuficiente na conta corrente.");
            }
            // Subtrai do saldo da conta
            conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));
            contaRepository.save(conta);
        }

    private void processarCompraCredito(CompraRequest request, Conta conta) {
        CartaoCredito cartao = cartaoRepository.findById(request.cartaoId())
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        // Valida se o limite total suporta a compra inteira
        if (cartao.getLimiteCredito().compareTo(request.valorTotal()) < 0) {
            throw new RuntimeException("Limite insuficiente.");
        }

        BigDecimal valorParcela = request.valorTotal().divide(
                BigDecimal.valueOf(request.parcelas()), 2, RoundingMode.HALF_UP
        );

        // O limite do cartão é consumido pelo valor TOTAL da compra imediatamente
        cartao.setLimiteCredito(cartao.getLimiteCredito().subtract(request.valorTotal()));
        cartaoRepository.save(cartao);

        LocalDate dataReferencia = LocalDate.now();

        for (int i = 0; i < request.parcelas(); i++) {
            // Calcula o mês da parcela
            LocalDate dataParcela = dataReferencia.plusMonths(i);

            // Se já passou do fechamento deste mês, a primeira parcela já vai para o próximo
            if (dataReferencia.getDayOfMonth() > cartao.getDiaFechamento() && i == 0) {
                dataParcela = dataParcela.plusMonths(1);
                // Ajusta a referência para as parcelas subsequentes seguirem a ordem
                dataReferencia = dataReferencia.plusMonths(1);
            } else if (i > 0) {
                // Para parcelas 2, 3... apenas segue o fluxo da data de referência ajustada
                dataParcela = dataReferencia.plusMonths(i);
            }

            Fatura fatura = buscarOuCriarFatura(cartao, dataParcela.getMonthValue(), dataParcela.getYear());

            Transacao t = new Transacao();
            t.setDescricao(request.descricao() + " (" + (i + 1) + "/" + request.parcelas() + ")");
            t.setValor(valorParcela);
            t.setData(LocalDateTime.now());
            t.setTipo("DESPESA");
            t.setConta(conta);
            t.setFatura( fatura);

            transacaoRepository.save(t);
        }
    }

    private Fatura buscarOuCriarFatura(CartaoCredito cartao, int mes, int ano) {
        return faturaRepository.findByCartaoCreditoAndMesAndAno(cartao, mes, ano)
                .orElseGet(() -> {
                    Fatura nova = new Fatura();
                    nova.setCartaoCredito(cartao);
                    nova.setMes(mes);
                    nova.setAno(ano);
                    nova.setStatus("ABERTA");
                    return faturaRepository.save(nova);
                });
    }
    }


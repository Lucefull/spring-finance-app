package com.lucefull.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_transacoes", schema = DB_SCHEMA)
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
    private String tipo; // RECEITA ou DESPESA


    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "conta_id") // Preenchido se for débito/depósito
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "compra_id") // FK para a compra original
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private Fatura fatura;
}
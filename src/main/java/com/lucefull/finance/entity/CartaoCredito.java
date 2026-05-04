package com.lucefull.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_cartoes_credito", schema = DB_SCHEMA)
@Data
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandeira;
    private BigDecimal limiteCredito;
    private Integer diaFechamento;
    private Integer diaVencimento;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
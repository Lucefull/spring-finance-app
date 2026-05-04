package com.lucefull.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_faturas", schema = DB_SCHEMA)
@Data
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mes;
    private Integer ano;
    private String status; // Ex: ABERTA, FECHADA, PAGA

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoCredito cartaoCredito;

    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private Fatura fatura;
}

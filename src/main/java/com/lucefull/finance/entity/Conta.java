package com.lucefull.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_contas", schema = DB_SCHEMA)
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo; // Ex: CORRENTE, POUPANÇA, CARTEIRA
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

package com.lucefull.finance.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_compras", schema = DB_SCHEMA)
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valorTotal;
    private Integer totalParcelas;
    private LocalDateTime dataCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Transacao> parcelas;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;
}

package com.lucefull.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import static com.lucefull.finance.utils.VariaveisGlobais.DB_SCHEMA;

@Entity
@Table(name = "tab_categorias", schema =DB_SCHEMA)
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String icone;

}

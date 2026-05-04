package com.lucefull.finance.repository;

import com.lucefull.finance.entity.CartaoCredito;
import com.lucefull.finance.entity.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    Optional<Fatura> findByCartaoCreditoAndMesAndAno(CartaoCredito cartao, int mes, int ano);
}


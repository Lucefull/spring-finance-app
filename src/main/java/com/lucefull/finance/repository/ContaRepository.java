package com.lucefull.finance.repository;


import com.lucefull.finance.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findAllByUsuarioId(UUID uuid);
}

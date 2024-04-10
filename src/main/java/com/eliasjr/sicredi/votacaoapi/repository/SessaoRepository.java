package com.eliasjr.sicredi.votacaoapi.repository;


import com.eliasjr.sicredi.votacaoapi.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query(value = "FROM Sessao se where se.pauta.id = :id")
    Optional<Sessao> findByIdPauta(Long id);
}

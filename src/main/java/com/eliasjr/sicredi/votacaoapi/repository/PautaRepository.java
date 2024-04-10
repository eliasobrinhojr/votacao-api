package com.eliasjr.sicredi.votacaoapi.repository;

import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query(value = "SELECT p FROM Pauta p " +
            "WHERE (p.id = :id or :id IS NULL) " +
            "AND (p.titulo LIKE CONCAT('%', UPPER(:titulo), '%') OR :titulo IS NULL) " +
            "AND (p.descricao LIKE CONCAT('%', UPPER(:descricao), '%') OR :descricao IS NULL) ")
    List<Pauta> list(Long id, String titulo, String descricao);

    @Query(value = "SELECT COUNT(vo) from Pauta pa " +
            "JOIN Sessao se ON se.pauta.id = pa.id " +
            "JOIN Voto vo ON vo.sessao.id = se.id " +
            "WHERE pa.id = :id and vo.voto = :voto " +
            "AND se.dataCreate = (SELECT MAX(ses.dataCreate) FROM Sessao ses WHERE ses.pauta.id = pa.id) ")
    Long countByIdAndVote(Long id, Boolean voto);

}

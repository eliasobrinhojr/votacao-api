package com.eliasjr.sicredi.votacaoapi.repository;

import com.eliasjr.sicredi.votacaoapi.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "FROM Voto v WHERE v.sessao.id = :id AND v.associado.cpf = :cpf")
    Optional<Voto> findBySessaoAndCpf(Long id, String cpf);

}

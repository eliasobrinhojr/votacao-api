package com.eliasjr.sicredi.votacaoapi.repository;

import com.eliasjr.sicredi.votacaoapi.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    @Query(value = "SELECT e FROM Associado e " +
            "WHERE (e.id = :id or :id IS NULL) " +
            "AND (e.nome LIKE CONCAT('%', UPPER(:nome), '%') OR :nome IS NULL) " +
            "AND (e.cpf LIKE :cpf OR :cpf IS NULL) ")
    List<Associado> list(Long id, String cpf, String nome);

    Optional<Associado> findByCpf(String cpf);
}

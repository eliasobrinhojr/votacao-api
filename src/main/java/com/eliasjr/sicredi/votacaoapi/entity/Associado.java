package com.eliasjr.sicredi.votacaoapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "associado", schema = "cooperativa")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @Column(insertable = false, updatable = false)
    private Timestamp dataCreate;

}

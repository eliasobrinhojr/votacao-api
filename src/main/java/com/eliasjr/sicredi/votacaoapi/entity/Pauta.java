package com.eliasjr.sicredi.votacaoapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "pauta", schema = "cooperativa")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String resultado;

    @Column(insertable = false, updatable = false)
    private Timestamp dataCreate;

}

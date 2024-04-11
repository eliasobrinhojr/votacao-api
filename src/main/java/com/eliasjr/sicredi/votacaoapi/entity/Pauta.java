package com.eliasjr.sicredi.votacaoapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String resultado;

    @Column(name = "data_create", nullable = false)
    private Timestamp dataCreate = new Timestamp(System.currentTimeMillis());

}

package com.eliasjr.sicredi.votacaoapi.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "votos", schema = "cooperativa")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean voto;

    @Column(insertable = false, updatable = false)
    private Timestamp dataCreate;

    @ManyToOne
    @JoinColumn(name = "associado_id", referencedColumnName = "id")
    private Associado associado;

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

}

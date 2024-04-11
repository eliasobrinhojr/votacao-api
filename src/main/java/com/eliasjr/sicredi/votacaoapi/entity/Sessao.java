package com.eliasjr.sicredi.votacaoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "sessao", schema = "cooperativa")
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long duration;

    @Column(name = "data_create", nullable = false, insertable = false, updatable = false)
    private Timestamp dataCreate;

    @ManyToOne()
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    public Boolean isActiveSession() {
        if (this.dataCreate != null) {
            return LocalDateTime.now().isBefore(this.dataCreate.toLocalDateTime().plusSeconds(this.duration));
        }
        return false;
    }
}

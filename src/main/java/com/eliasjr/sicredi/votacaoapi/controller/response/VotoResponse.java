package com.eliasjr.sicredi.votacaoapi.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoResponse {
    private Long id;
    private String voto;
    private Timestamp dataCriacao;
    private String cpfAssociado;
    private Long idPauta;
}

package com.eliasjr.sicredi.votacaoapi.controller.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessaoResponse {
    private Long id;
    private Long duration;
    private Timestamp dataCreate;
    private PautaResponse pauta;
}
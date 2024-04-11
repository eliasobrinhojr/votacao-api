package com.eliasjr.sicredi.votacaoapi.controller.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociadoResponse {
    private String nome;
    private String cpf;
    private Timestamp dataCreate;
}

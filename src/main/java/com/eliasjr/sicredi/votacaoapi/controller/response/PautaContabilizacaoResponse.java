package com.eliasjr.sicredi.votacaoapi.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PautaContabilizacaoResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String resultado;
    private Long pros;
    private Long contras;
}

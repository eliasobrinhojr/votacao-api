package com.eliasjr.sicredi.votacaoapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PautaContabilizacaoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String resultado;
    private Long pros;
    private Long contras;
}

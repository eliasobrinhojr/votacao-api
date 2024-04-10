package com.eliasjr.sicredi.votacaoapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaRequest {

    @NotBlank(message = "Campo não pode ser vazio.")
    private String titulo;

    @NotBlank(message = "Campo não pode ser vazio.")
    private String descricao;

}

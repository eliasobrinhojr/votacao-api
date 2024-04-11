package com.eliasjr.sicredi.votacaoapi.controller.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoRequest {

    @NotNull(message = "Campo não pode ser nulo.")
    private Long sessaoId;

    @NotBlank(message = "Campo não pode estar vazio.")
    private String cpf;

    @NotNull(message = "Campo não pode ser nulo.")
    private Boolean voto;
}


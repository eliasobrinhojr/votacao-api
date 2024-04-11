package com.eliasjr.sicredi.votacaoapi.controller.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoRequest {

    @NotBlank(message = "Campo não pode estar vazio.")
    private String nome;

    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve ser apenas numérico.")
    private String cpf;
}

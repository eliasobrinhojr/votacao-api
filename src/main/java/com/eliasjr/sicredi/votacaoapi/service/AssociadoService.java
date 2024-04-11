package com.eliasjr.sicredi.votacaoapi.service;

import com.eliasjr.sicredi.votacaoapi.controller.request.AssociadoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.AssociadoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Associado;

import java.util.List;

public interface AssociadoService {

    void create(AssociadoRequest associateDTO);

    List<AssociadoResponse> list(Long id, String cpf, String nome);

    Associado findByCpf(String cpf);

}

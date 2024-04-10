package com.eliasjr.sicredi.votacaoapi.service;

import com.eliasjr.sicredi.votacaoapi.controller.request.SessaoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.SessaoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Sessao;

import java.util.List;

public interface SessaoService {

    void create(SessaoRequest sessaoDTO);

    Sessao findById(Long id);

    Sessao findByIdPauta(Long id);

    List<SessaoResponse> list();

}

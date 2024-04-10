package com.eliasjr.sicredi.votacaoapi.service;

import com.eliasjr.sicredi.votacaoapi.controller.request.PautaRequest;
import com.eliasjr.sicredi.votacaoapi.controller.dto.PautaContabilizacaoDTO;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;

import java.util.List;

public interface PautaService {

    void create(PautaRequest decisaoDTO);

    Pauta findById(Long id);

    PautaContabilizacaoDTO accounting(Long id);

    List<PautaResponse> list(Long id, String titulo, String descricao);
}

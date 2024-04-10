package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.response.PautaContabilizacaoResponse;
import com.eliasjr.sicredi.votacaoapi.controller.request.PautaRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsGenericExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.PautaRepository;
import com.eliasjr.sicredi.votacaoapi.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void create(PautaRequest pautaDTO) {
        pautaRepository.saveAndFlush(objectMapper.convertValue(pautaDTO, Pauta.class));
    }

    @Override
    public Pauta findById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new ValidationsGenericExceptions("Pauta não encontrada."));
    }

    @Override
    public List<PautaResponse> list(Long id, String titulo, String descricao) {
        return pautaRepository.list(id, titulo, descricao).stream()
                .map(pauta -> objectMapper.convertValue(pauta, PautaResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PautaContabilizacaoResponse accounting(Long id) {
        Pauta pauta = findById(id);

        Long pros = pautaRepository.countByIdAndVote(id, true);
        Long contras = pautaRepository.countByIdAndVote(id, false);

        pauta.setResultado(pros > contras ? "SIM" : "NÃO");
        Pauta pautaAtualizada = pautaRepository.save(pauta);

        return PautaContabilizacaoResponse.builder()
                .id(pautaAtualizada.getId())
                .titulo(pautaAtualizada.getTitulo())
                .descricao(pautaAtualizada.getDescricao())
                .resultado(pautaAtualizada.getResultado())
                .pros(pros)
                .contras(contras)
                .build();
    }
}

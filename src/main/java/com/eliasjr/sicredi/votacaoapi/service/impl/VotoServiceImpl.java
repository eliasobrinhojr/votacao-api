package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.request.VotoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.VotoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Voto;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.VotoRepository;
import com.eliasjr.sicredi.votacaoapi.service.AssociadoService;
import com.eliasjr.sicredi.votacaoapi.service.SessaoService;
import com.eliasjr.sicredi.votacaoapi.service.VotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;
    private final AssociadoService associadoService;
    private final ObjectMapper objectMapper;

    @Override
    public void create(VotoRequest novoVoto) {
        log.info("Criando novo voto, CPF = {}", novoVoto.getCpf());

        Voto voto = objectMapper.convertValue(novoVoto, Voto.class);
        voto.setAssociado(associadoService.findByCpf(novoVoto.getCpf()));
        voto.setSessao(sessaoService.findById(novoVoto.getSessaoId()));

        if (votoRepository.findBySessaoAndCpf(voto.getSessao().getId(), voto.getAssociado().getCpf()).isPresent()) {
            throw new ValidationsExceptions("CPF ja realizou voto na sessao.");
        }

        if (!voto.getSessao().isActiveSession()) {
            throw new ValidationsExceptions("Votacao foi encerrada.");
        }

        votoRepository.saveAndFlush(voto);
    }

    @Override
    public List<VotoResponse> list() {
        return votoRepository.findAll()
                .stream()
                .map(voto -> VotoResponse.builder()
                        .id(voto.getId())
                        .voto(voto.getVoto() ? "SIM" : "NAO")
                        .dataCriacao(voto.getDataCreate())
                        .cpfAssociado(voto.getAssociado().getCpf())
                        .idPauta(voto.getSessao().getPauta().getId())
                        .build())
                .collect(Collectors.toList());
    }
}

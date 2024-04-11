package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.request.SessaoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.SessaoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import com.eliasjr.sicredi.votacaoapi.entity.Sessao;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.SessaoRepository;
import com.eliasjr.sicredi.votacaoapi.service.PautaService;
import com.eliasjr.sicredi.votacaoapi.service.SessaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;
    private final ObjectMapper objectMapper;

    @Value("${session.duration}")
    private Long SESSION_DURATION;

    @Override
    public void create(SessaoRequest sessaoDTO) {

        Pauta pautaEntity = pautaService.findById(sessaoDTO.getPautaId());

        log.info("Cadastro Nova Sessao Para Pauta ID = {}", pautaEntity.getId());

        sessaoRepository.saveAndFlush(Sessao.builder()
                .duration(Objects.isNull(sessaoDTO.getDuracao()) || sessaoDTO.getDuracao() == 0 ? SESSION_DURATION : sessaoDTO.getDuracao())
                .dataCreate(new Timestamp(System.currentTimeMillis()))
                .pauta(pautaEntity).build());
    }

    @Override
    public Sessao findById(Long id) {
        log.info("Busca Sessao ID = {}", id);

        return sessaoRepository.findById(id).orElseThrow(
                () -> new ValidationsExceptions("Sessão nao encontrada."));
    }

    @Override
    public Sessao findByIdPauta(Long id) {

        log.info("Busca Sessao Pauta ID = {}", id);

        return sessaoRepository.findByIdPauta(id).orElseThrow(
                () -> new ValidationsExceptions("Sessão nao encontrada."));
    }

    @Override
    public List<SessaoResponse> list() {
        return sessaoRepository.findAll()
                .stream()
                .map(sessao -> objectMapper.convertValue(sessao, SessaoResponse.class)).collect(Collectors.toList());
    }
}

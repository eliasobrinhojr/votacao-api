package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.request.PautaRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaContabilizacaoResponse;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.PautaRepository;
import com.eliasjr.sicredi.votacaoapi.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void create(PautaRequest pautaDTO) {
        log.info("Criando nova pauta = {}", pautaDTO.getTitulo());
        pautaRepository.saveAndFlush(objectMapper.convertValue(pautaDTO, Pauta.class));
    }

    @Override
    public Pauta findById(Long id) {
        log.info("Busca Pauta ID = {}", id);
        return pautaRepository.findById(id)
                .orElseThrow(() -> new ValidationsExceptions("Pauta nao encontrada."));
    }

    @Override
    public List<PautaResponse> list(Long id, String titulo, String descricao) {
        return pautaRepository.list(id, titulo, descricao).stream()
                .map(pauta -> objectMapper.convertValue(pauta, PautaResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PautaContabilizacaoResponse accounting(Long id) {
        log.info("Contabilizacao Pauta ID = {}", id);
        Pauta pauta = findById(id);

        Long pros = pautaRepository.countByIdAndVote(id, true);
        Long contras = pautaRepository.countByIdAndVote(id, false);

        pauta.setResultado(pros > contras ? "SIM" : "NÃO");
        Pauta pautaAtualizada = pautaRepository.save(pauta);

        // TODO postar na fila

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

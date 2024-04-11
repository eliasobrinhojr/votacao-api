package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.request.AssociadoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.AssociadoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Associado;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsGenericExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.AssociadoRepository;
import com.eliasjr.sicredi.votacaoapi.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void create(AssociadoRequest associateDTO) {
        if (associadoRepository.findByCpf(associateDTO.getCpf()).isPresent()) {
            throw new ValidationsGenericExceptions("Este associado já está cadastrado.");
        }

        associadoRepository.saveAndFlush(objectMapper.convertValue(associateDTO, Associado.class));
    }

    @Override
    public Associado findByCpf(String cpf) {
        return associadoRepository.findByCpf(cpf).orElseThrow(() -> new ValidationsGenericExceptions(
                "Associado não encontrado."));
    }

    @Override
    public List<AssociadoResponse> list(Long id, String cpf, String nome) {
        return associadoRepository.list(id, cpf, nome).stream()
                .map(associado -> objectMapper.convertValue(associado, AssociadoResponse.class))
                .collect(Collectors.toList());
    }
}

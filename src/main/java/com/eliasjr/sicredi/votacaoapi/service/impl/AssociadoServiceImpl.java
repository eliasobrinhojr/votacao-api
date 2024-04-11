package com.eliasjr.sicredi.votacaoapi.service.impl;

import com.eliasjr.sicredi.votacaoapi.controller.request.AssociadoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.AssociadoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Associado;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsExceptions;
import com.eliasjr.sicredi.votacaoapi.repository.AssociadoRepository;
import com.eliasjr.sicredi.votacaoapi.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void create(AssociadoRequest associateDTO) {
        log.info("Cadastro novo Associado CPF = {}", associateDTO.getCpf());

        if (associadoRepository.findByCpf(associateDTO.getCpf()).isPresent()) {
            throw new ValidationsExceptions("CPF ja cadastrado.");
        }

        // TODO chamada p outro service via feign para validar cpf valido
        associadoRepository.saveAndFlush(objectMapper.convertValue(associateDTO, Associado.class));
    }

    @Override
    public Associado findByCpf(String cpf) {
        return associadoRepository.findByCpf(cpf).orElseThrow(() -> new ValidationsExceptions(
                "Associado nao encontrado."));
    }

    @Override
    public List<AssociadoResponse> list(Long id, String cpf, String nome) {
        return associadoRepository.list(id, cpf, nome).stream()
                .map(associado -> objectMapper.convertValue(associado, AssociadoResponse.class))
                .collect(Collectors.toList());
    }
}

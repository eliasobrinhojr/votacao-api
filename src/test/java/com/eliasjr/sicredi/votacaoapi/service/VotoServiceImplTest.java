package com.eliasjr.sicredi.votacaoapi.service;

import com.eliasjr.sicredi.votacaoapi.controller.request.VotoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.VotoResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Associado;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import com.eliasjr.sicredi.votacaoapi.entity.Sessao;
import com.eliasjr.sicredi.votacaoapi.entity.Voto;
import com.eliasjr.sicredi.votacaoapi.exception.ValidationsExceptions;
import com.eliasjr.sicredi.votacaoapi.mapper.AssociadoMapper;
import com.eliasjr.sicredi.votacaoapi.mapper.SessaoMapper;
import com.eliasjr.sicredi.votacaoapi.mapper.VotoMapper;
import com.eliasjr.sicredi.votacaoapi.repository.VotoRepository;
import com.eliasjr.sicredi.votacaoapi.service.impl.VotoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VotoServiceImplTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private VotoServiceImpl votoService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVoto_Success() {

        VotoRequest novoVoto = VotoMapper.INSTANCE.votoRequest();
        Sessao sessao = SessaoMapper.INSTANCE.sessao(new Timestamp(System.currentTimeMillis()));
        Associado associado = AssociadoMapper.INSTANCE.associado();

        Voto voto = VotoMapper.INSTANCE.voto(associado, sessao);

        when(objectMapper.convertValue(novoVoto, Voto.class)).thenReturn(voto);
        when(associadoService.findByCpf(novoVoto.getCpf())).thenReturn(associado);
        when(sessaoService.findById(novoVoto.getSessaoId())).thenReturn(sessao);
        when(votoRepository.findBySessaoAndCpf(anyLong(), anyString())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> votoService.create(novoVoto));

        verify(votoRepository).saveAndFlush(voto);
    }

    @Test
    void createVoto_Fail_AssociadoJaVotou() {

        VotoRequest novoVoto = VotoMapper.INSTANCE.votoRequest();
        Sessao sessao = SessaoMapper.INSTANCE.sessao(new Timestamp(System.currentTimeMillis()));
        Associado associado = AssociadoMapper.INSTANCE.associado();

        Voto voto = VotoMapper.INSTANCE.voto(associado, sessao);
        when(objectMapper.convertValue(novoVoto, Voto.class)).thenReturn(voto);
        when(associadoService.findByCpf(novoVoto.getCpf())).thenReturn(associado);
        when(sessaoService.findById(novoVoto.getSessaoId())).thenReturn(sessao);
        when(votoRepository.findBySessaoAndCpf(anyLong(), anyString())).thenReturn(Optional.of(voto));

        Exception exception = assertThrows(ValidationsExceptions.class, () -> {
            votoService.create(novoVoto);
        });

        assertEquals("Associado já cadastrado na sessão.", exception.getMessage());
    }

    @Test
    void createVoto_Fail_SessaoFechada() {
        VotoRequest novoVoto = VotoMapper.INSTANCE.votoRequest();
        Sessao sessao = SessaoMapper.INSTANCE.sessao(null);
        Associado associado = AssociadoMapper.INSTANCE.associado();

        Voto voto = VotoMapper.INSTANCE.voto(associado, sessao);

        when(objectMapper.convertValue(novoVoto, Voto.class)).thenReturn(voto);
        when(associadoService.findByCpf(novoVoto.getCpf())).thenReturn(associado);
        when(sessaoService.findById(novoVoto.getSessaoId())).thenReturn(sessao);
        when(votoRepository.findBySessaoAndCpf(anyLong(), anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ValidationsExceptions.class, () -> {
            votoService.create(novoVoto);
        });

        assertEquals("Votação foi encerrada.", exception.getMessage());
    }

    @Test
    void list_Success() {
        List<Voto> votos = createVotosList();
        when(votoRepository.findAll()).thenReturn(votos);

        List<VotoResponse> responses = votoService.list();

        assertNotNull(responses);
        assertEquals(votos.size(), responses.size());
    }

    public List<Voto> createVotosList() {
        List<Voto> votos = new ArrayList<>();
        Pauta pauta = new Pauta();

        pauta.setId(1L);
        pauta.setResultado("5");
        pauta.setDescricao("teste");
        pauta.setDescricao("descricao teste");

        Sessao sessao = Sessao.builder()
                .id(1L)
                .duration(60L)
                .pauta(pauta)
                .dataCreate(new Timestamp(System.currentTimeMillis() - 10000))
                .build();

        Associado associado1 = new Associado();
        associado1.setId(1L);
        associado1.setDataCreate(new Timestamp(System.currentTimeMillis() - 5000));
        associado1.setCpf("1332131231");
        associado1.setNome("Fulano");

        Associado associado2 = new Associado();
        associado2.setId(2L);
        associado2.setDataCreate(new Timestamp(System.currentTimeMillis() - 4000));
        associado2.setCpf("987654321");
        associado2.setNome("Ciclano");

        // Criação dos Votos
        Voto voto1 = new Voto();
        voto1.setAssociado(associado1);
        voto1.setVoto(true);
        voto1.setSessao(sessao);
        voto1.setDataCreate(new Timestamp(System.currentTimeMillis()));
        voto1.setId(1L);

        Voto voto2 = new Voto();
        voto2.setAssociado(associado2);
        voto2.setVoto(false);
        voto2.setSessao(sessao);
        voto2.setDataCreate(new Timestamp(System.currentTimeMillis() + 1000));
        voto2.setId(2L);

        votos.add(voto1);
        votos.add(voto2);

        return votos;
    }
}

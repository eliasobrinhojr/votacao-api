package com.eliasjr.sicredi.votacaoapi.controller;

import com.eliasjr.sicredi.votacaoapi.controller.request.SessaoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.SessaoResponse;
import com.eliasjr.sicredi.votacaoapi.service.SessaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/sessao")
@RestController
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessoesService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody SessaoRequest sessaoDTO) {
        sessoesService.create(sessaoDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SessaoResponse>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(sessoesService.list());
    }
}

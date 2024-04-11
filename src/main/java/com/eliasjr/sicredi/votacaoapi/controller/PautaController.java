package com.eliasjr.sicredi.votacaoapi.controller;

import com.eliasjr.sicredi.votacaoapi.controller.request.PautaRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaContabilizacaoResponse;
import com.eliasjr.sicredi.votacaoapi.controller.response.PautaResponse;
import com.eliasjr.sicredi.votacaoapi.entity.Pauta;
import com.eliasjr.sicredi.votacaoapi.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/pauta")
@RestController
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PautaRequest pautaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.create(pautaDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PautaResponse>> list(
            @RequestParam(required = false, value = "id") Long id,
            @RequestParam(required = false, value = "titulo") String titulo,
            @RequestParam(required = false, value = "descricao") String descricao) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.list(id, titulo, descricao));
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<PautaContabilizacaoResponse> count(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.accounting(id));
    }

}

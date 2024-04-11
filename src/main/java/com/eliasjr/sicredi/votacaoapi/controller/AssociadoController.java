package com.eliasjr.sicredi.votacaoapi.controller;

import com.eliasjr.sicredi.votacaoapi.controller.request.AssociadoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.AssociadoResponse;
import com.eliasjr.sicredi.votacaoapi.service.AssociadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/associado")
@RestController
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody AssociadoRequest associadoCadastroDTO) {
        associadoService.create(associadoCadastroDTO);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AssociadoResponse>> list(
            @RequestParam(required = false, value = "id") Long id,
            @RequestParam(required = false, value = "cpf") String cpf,
            @RequestParam(required = false, value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(associadoService.list(id, cpf, nome));
    }
}

package com.eliasjr.sicredi.votacaoapi.controller;

import com.eliasjr.sicredi.votacaoapi.controller.request.VotoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.VotoResponse;
import com.eliasjr.sicredi.votacaoapi.service.VotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/votos")
@RestController
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votosService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody VotoRequest votoDTO) {
        votosService.create(votoDTO);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<VotoResponse>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(votosService.list());
    }
}

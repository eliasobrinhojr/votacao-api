package com.eliasjr.sicredi.votacaoapi.service;


import com.eliasjr.sicredi.votacaoapi.controller.request.VotoRequest;
import com.eliasjr.sicredi.votacaoapi.controller.response.VotoResponse;

import java.util.List;

public interface VotoService {

    void create(VotoRequest votoCadastro);

    List<VotoResponse> list();
}
